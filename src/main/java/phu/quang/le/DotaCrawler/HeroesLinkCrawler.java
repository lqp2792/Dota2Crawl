package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HeroesLinkCrawler {

	public static Map<Integer, String> heroesLink = new ConcurrentHashMap<> ();

	public static void main (String[] args) {
		HeroesLinkCrawler.startCrawling ("http://dota2.gamepedia.com/Heroes");
	}

	public static void startCrawling (String url) {
		String heroName;
		String heroUrl;
		String heroImageUrl;
		String heroIntro;
		AtomicInteger id = new AtomicInteger ();
		try {
			Document doc = Jsoup
					.connect (url)
					.userAgent (
							"Mozilla/5.0 (X11; Linux x86_64) "
									+ "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/36.0.1985.125 Safari/537.36")
					.timeout (10000).get ();
			Elements heroTables = doc.select ("table[width=100%]");
			for (int i = 0 ; i < heroTables.size () ; i++) {
				Element heroTable = heroTables.get (i);
				Elements heroColumns = heroTable.select ("td[width=50%]");
				Elements heroLinks = heroTable.select (
						"div[style=font-weight:bold;]").select ("a");
				Elements heroImageLinks = heroTable
						.select ("div[style=float:left; padding:8px;]");
				for (int j = 0, n = heroLinks.size () ; j < n ; j++) {
					Element heroLink = heroLinks.get (j);
					Element heroColumn = heroColumns.get (j);
					heroName = heroLink.attr ("title");
					heroUrl = heroLink.attr ("abs:href");
					heroImageUrl = heroImageLinks.select (
							"img[alt=" + heroName + "]").attr ("src");
					System.out.println (heroName);
					System.out.println (heroImageUrl);
					System.out.println (heroColumn.ownText ());
					System.out.println ();
				}
			}
		} catch (IOException e) {
			System.err.println (e);
		}
		// URL uriLink = new URL (url);
		// URLConnection connection = uriLink.openConnection ();
		// connection.addRequestProperty ("User-agent",
		// "Mozilla/5.0 (X11; Linux x86_64) "
		// + "AppleWebKit/537.36 (KHTML, like Gecko) "
		// + "Chrome/36.0.1985.125 Safari/537.36");
		// Parser parser = new Parser (connection);
		// NodeList list = parser.extractAllNodesThatMatch (new NodeClassFilter
		// (
		// TableTag.class));
		// // System.out.println("Table: " + list.size());
		// list = list
		// .extractAllNodesThatMatch (new HasAttributeFilter ("width", "100%"));
		// // System.out.println("Table: " + list.size());
		// for (int i = 0, n = list.size (); i < n; i++) {
		// TableTag table = (TableTag) list.elementAt (i);
		// // System.out.println("\tTable " + (i+1) + ": Child: " +
		// // table.getChildCount());
		// Parser p = new Parser (table.getChildren ().toHtml ());
		// NodeList childList = p.extractAllNodesThatMatch (new NodeClassFilter
		// (
		// LinkTag.class));
		// childList = childList.extractAllNodesThatMatch (new NotFilter (
		// new LinkStringFilter ("version")));
		// // System.out.println("\t\tLink: " + childList.size());
		// for (int j = 0, m = childList.size (); j < m; j++) {
		// boolean isExisted = false;
		// LinkTag extracted = (LinkTag) childList.elementAt (j);
		// String link = "http://dota2.gamepedia.com" + extracted.extractLink
		// ();
		// // System.out.println("\t\t\tid: " + j + " Link: " + link);
		// // System.out.println("\t\t\tHeroesLink: " +
		// // heroesLink.size());
		// if (heroesLink.size () == 0) {
		// heroesLink.put (id.getAndIncrement (), link);
		// } else {
		// for (Map.Entry<Integer, String> entry : heroesLink.entrySet ()) {
		// if (entry.getValue ().contains (link)) {
		// isExisted = true;
		// }
		// }
		// if (!isExisted)
		// heroesLink.put (id.getAndIncrement (), link);
		// }
		// }
		// }
		// int i = 0;
		// for (Map.Entry<Integer, String> entry : heroesLink.entrySet ()) {
		// System.out.println ("Entry: " + "id: " + entry.getKey () + " "
		// + entry.getValue ());
		// i++;
		// }
		// System.out.println ("Map size: " + i);
		// } catch (MalformedURLException e) {
		// System.err.println (e);
		// } catch (IOException e) {
		// System.err.println (e);
		// } catch (ParserException e) {
		// System.err.println (e);
	}
}
