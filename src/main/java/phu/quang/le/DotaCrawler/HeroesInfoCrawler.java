package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import phu.quang.le.Dao.Hero;
import phu.quang.le.Dao.HeroStat;
import phu.quang.le.Dao.LevelStat;
import phu.quang.le.SimpleVisitor.AbilityVisitor;

public class HeroesInfoCrawler {

	public static void startCrawling (String url) {
		Hero hero = new Hero ();
		HeroStat heroStat = new HeroStat ();
		List<LevelStat> levelStats = new ArrayList<> ();

		try {
			URLConnection connection = new URL (url).openConnection ();
			connection.addRequestProperty ("User-agent",
					"Mozilla/5.0 (X11; Linux x86_64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) "
							+ "Chrome/36.0.1985.125 Safari/537.36");
			Parser parser = new Parser (connection);
			NodeList tableNodeList = parser
					.extractAllNodesThatMatch (new NodeClassFilter (
							TableTag.class));
			NodeList infoNodeList = tableNodeList
					.extractAllNodesThatMatch (new HasAttributeFilter ("class",
							"infobox"));
			System.out.println (infoNodeList.size ());
			NodeList abilityNodeList = tableNodeList
					.extractAllNodesThatMatch (new HasAttributeFilter ("style",
							"border:0;padding:0;margin:0;margin-bottom:1em;"));
			System.out.println (abilityNodeList.size ());
			// statRelateNodeList.visitAllNodesWith(new SimpleVisitor(heroStat,
			// hero, levelStats));
			abilityNodeList.visitAllNodesWith (new AbilityVisitor ());
		} catch (IOException e) {
			System.err.println (e);
		} catch (ParserException e) {
			System.err.println (e);
		}
	}

	public static void main (String[] args) {
		String url = "http://dota2.gamepedia.com/Lich";
		startCrawling (url);
	}
}
