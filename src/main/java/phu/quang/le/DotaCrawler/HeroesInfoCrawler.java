package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import phu.quang.le.Dao.HeroStat;
import phu.quang.le.SimpleVisitor.SimpleVisitor;


public class HeroesInfoCrawler {
	
	
	public static void startCrawling(String url) {
		HeroStat heroStat = new HeroStat();
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.addRequestProperty("User-agent", "Mozilla/5.0 (X11; Linux x86_64) "
					+ "AppleWebKit/537.36 (KHTML, like Gecko) "
					+ "Chrome/36.0.1985.125 Safari/537.36");
			Parser parser = new Parser(connection);
			NodeList statRelateNodeList = parser.extractAllNodesThatMatch(new AndFilter(
					new NodeClassFilter(TableTag.class), new HasAttributeFilter("class", "infobox")));
			statRelateNodeList.visitAllNodesWith(new SimpleVisitor(heroStat));
//			ImageTag extracted = (ImageTag) imageList.elementAt(0);
//			String imgUrl = extracted.getImageURL();
//			System.out.println(extracted.getImageURL());
//			
//			InputStream in = new BufferedInputStream(new URL(imgUrl).openStream());
//			OutputStream out = new BufferedOutputStream(new FileOutputStream("Luna.png"));
//			int i=0;
//			while((i = in.read()) != -1) {
//				out.write(i);
////			}
//			in.close();
//			out.flush();
//			out.close();
		} catch (IOException e) {
			System.err.println(e);
		} catch (ParserException e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
		String url = "http://dota2.gamepedia.com/Disruptor";
		startCrawling(url);
	}
}
