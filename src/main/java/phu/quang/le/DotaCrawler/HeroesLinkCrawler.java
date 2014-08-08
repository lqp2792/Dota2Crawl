package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import phu.quang.le.Util.DBUtility;

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
		// 1: Streng, 2: Agility, 3: Intelligent
		int type = 0;
		try {
			Document doc = Jsoup
					.connect (url)
					.userAgent (
							"Mozilla/5.0 (X11; Linux x86_64) "
									+ "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/36.0.1985.125 Safari/537.36")
					.timeout (10000).get ();
			Elements heroTables = doc.select ("table[width=100%]");
			for (int i = 0; i < heroTables.size (); i++) {
				Element heroTable = heroTables.get (i);
				type = i + 1;
				Elements heroLinks = heroTable.select ("div[style=font-weight:bold;]")
						.select ("a");
				Elements heroImageLinks = heroTable
						.select ("div[style=float:left; padding:8px;]");
				for (int j = 0, n = heroLinks.size (); j < n; j++) {
					Element heroLink = heroLinks.get (j);
					heroName = heroLink.attr ("title");
					heroUrl = heroLink.attr ("abs:href");
					heroImageUrl = heroImageLinks.select ("img[alt=" + heroName + "]")
							.attr ("src");
					Element heroColumn = heroLink.parent ().parent ();
					heroIntro = heroColumn.ownText ();
					DBUtility.insertHeroLink (heroName, heroIntro, heroUrl, heroImageUrl,
							type);
				}
			}
		} catch (IOException e) {
			System.err.println (e);
		}
	}
}
