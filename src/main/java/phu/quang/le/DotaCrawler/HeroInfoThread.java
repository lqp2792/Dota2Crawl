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
import phu.quang.le.Dao.SummonStat;
import phu.quang.le.SimpleVisitor.HeroInfoVisitor;

public class HeroInfoThread implements Runnable {
	public String url;
	public Hero hero = new Hero ();
	public HeroStat heroStat = new HeroStat ();
	public SummonStat summonStat = new SummonStat ();
	public List<LevelStat> levelStats = new ArrayList<> (); 
	
	public HeroInfoThread (String url) {
		this.url = url;
	}

	@Override
	public void run () {
		try {
			URLConnection connection = new URL (url).openConnection ();
			connection.addRequestProperty ("User-agent",
					"Mozilla/5.0 (X11; Linux x86_64) "
							+ "AppleWebKit/537.36 (KHTML, like Gecko) "
							+ "Chrome/36.0.1985.125 Safari/537.36");
			Parser parser = new Parser (connection);
			NodeList tableNodeList = parser
					.extractAllNodesThatMatch (new NodeClassFilter (TableTag.class));
			NodeList infoNodeList = tableNodeList
					.extractAllNodesThatMatch (new HasAttributeFilter ("class", "infobox"));
			NodeList abilityNodeList = tableNodeList
					.extractAllNodesThatMatch (new HasAttributeFilter ("style",
							"border:0;padding:0;margin:0;margin-bottom:1em;"));
			if(infoNodeList.size () == 1) {
				infoNodeList.visitAllNodesWith (new HeroInfoVisitor (heroStat, hero, levelStats));
			}
			if(infoNodeList.size () > 1) {
				infoNodeList.visitAllNodesWith (new HeroInfoVisitor (heroStat, hero, levelStats, summonStat));
			}
					
			// abilityNodeList.visitAllNodesWith (new AbilityVisitor (skills));
		} catch (IOException e) {
			System.err.println (e);
		} catch (ParserException e) {
			System.err.println (e);
		}
	}
}
