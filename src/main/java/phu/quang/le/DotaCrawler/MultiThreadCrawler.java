package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Multithread crawler dota2
 * 
 * @author Phu Le
 *
 */
public class MultiThreadCrawler {
	public static String startUrl = "http://dota2.gamepedia.com/Heroes";

	public static void main (String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool (5);
		
		try {
			URL urlLink = new URL (startUrl);
			URLConnection connection = urlLink.openConnection ();
			connection.addRequestProperty ("User-agent",
					"Mozilla/5.0 (X11; Linux x86_64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) "
							+ "Chrome/36.0.1985.125 Safari/537.36");
			Parser parser = new Parser (connection);
			Map<Integer, String> heroLinks = dotaSpider (parser);

			for (Map.Entry<Integer, String> entry : heroLinks.entrySet ()) {
				HeroInfoThread worker = new HeroInfoThread (entry.getValue ());
				executorService.execute (worker);
			}
		
		} catch (MalformedURLException e) {
			System.err.println (e);
		} catch (IOException e) {
			System.err.println (e);
		} catch (ParserException e) {
			System.err.println (e);
		}
	}

	public static Map<Integer, String> dotaSpider (Parser parser) throws ParserException {
		Map<Integer, String> heroLinks = new HashMap<> ();
		AtomicInteger id = new AtomicInteger ();

		NodeList tableList = parser.parse (new AndFilter (new NodeClassFilter (
				TableTag.class), new HasAttributeFilter ("width", "100%")));

		// Traverse all table node have attribute width="100%"
		for (int i = 0, n = tableList.size (); i < n; i++) {
			TableTag table = (TableTag) tableList.elementAt (i);
			Parser p = new Parser (table.getChildren ().toHtml ());
			NodeList linkList = p.extractAllNodesThatMatch (new AndFilter (
					new NodeClassFilter (LinkTag.class), new NotFilter (
							new LinkStringFilter ("version"))));
			// Traverse all link node doesnt have string "vesion" in link
			for (int j = 0, m = linkList.size (); j < m; j++) {
				boolean isExisted = false;
				LinkTag extracted = (LinkTag) linkList.elementAt (j);
				String link = "http://dota2.gamepedia.com" + extracted.extractLink ();

				if (heroLinks.size () == 0) {
					heroLinks.put (id.getAndIncrement (), link);
				} else {
					for (Map.Entry<Integer, String> entry : heroLinks.entrySet ()) {
						if (entry.getValue ().contains (link)) {
							isExisted = true;
						}
					}
					if (!isExisted)
						heroLinks.put (id.getAndIncrement (), link);
				}
			}
		}

		return heroLinks;
	}
}
