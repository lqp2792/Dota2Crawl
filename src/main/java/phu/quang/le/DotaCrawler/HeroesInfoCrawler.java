package phu.quang.le.DotaCrawler;

import java.io.IOException;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import phu.quang.le.Dao.Hero;
import phu.quang.le.Dao.HeroStat;

public class HeroesInfoCrawler {

	public static void startCrawling (String url) {
		Hero hero = new Hero ();
		try {
			Document doc = Jsoup
					.connect (url)
					.userAgent (
							"Mozilla/5.0 (X11; Linux x86_64) "
									+ "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/36.0.1985.125 Safari/537.36")
					.timeout (10000).get ();
			Elements heroInfoBox = doc.select ("table[class=infobox]");
			getHeroInfoBox (heroInfoBox, hero);
		} catch (IOException e) {
			System.err.println (e);
		}
	}

	public static void getHeroInfoBox (Elements heroInfoBox, Hero hero) {
		Element heroInfo = heroInfoBox.get (0);
		getHeroInfo (heroInfo, hero);
		int num = 1;
		while (num < heroInfoBox.size ()) {
			Element unitInfo = heroInfoBox.get (num);
			getUnitInfo (unitInfo, hero);
			num++;
		}
	}

	public static Hero getHeroInfo (Element info, Hero hero) {
		StringTokenizer tokens;
		HeroStat heroStat = new HeroStat ();
		Elements ths = info.select ("table[width=100%]").select ("th[align=center]");
		String heroName = ths.get (0).text ();
		String heroImageUrl = info.select ("img[alt=" + heroName + ".png]").attr ("src");
		System.out.println (heroName);
		System.out.println (heroImageUrl);
		for (int i = 1; i < ths.size (); i++) {
			Element th = ths.get (i);
			tokens = new StringTokenizer (th.text (), "+ ");
			double beginStat = Double.parseDouble (tokens.nextToken ());
			double statPerLevel = Double.parseDouble (tokens.nextToken ());
			if (i == 1) {
				heroStat.setBeginStrength (beginStat);
				heroStat.setStrPerLevel (statPerLevel);
				System.out.println ("Strength: " + heroStat.getBeginStrength () + " + "
						+ heroStat.getStrPerLevel ());
			}
			if (i == 2) {
				heroStat.setBeginAgility (beginStat);
				heroStat.setAgiPerLevel (statPerLevel);
				System.out.println ("Agility: " + heroStat.getBeginAgility () + " + "
						+ heroStat.getAgiPerLevel ());
			}
			if (i == 3) {
				heroStat.setBeginIntel (beginStat);
				heroStat.setIntPerLevel (statPerLevel);
				System.out.println ("Intelligent: " + heroStat.getBeginIntel () + " + "
						+ heroStat.getIntPerLevel ());
			}
		}
		Elements statTables = info
				.select ("table[width=100%][style=border-collapse: collapse;]");
		for (int i = 0; i < statTables.size (); i++) {
			Element statTable = statTables.get (i);
			if (i == 0) {
			}
			if (i == 1) {
				Elements statNameTds = statTable.select ("td[style=padding-left: 2em]");
				Elements statValueTds = statTable.select ("td").not (
						"[style=padding-left: 2em]");
				System.out.println (statNameTds.size ());
				System.out.println (statValueTds.size ());
			}
		}
		return hero;
	}

	public static void getUnitInfo (Element Info, Hero hero) {
	}

	public static void main (String[] args) {
		String url = "http://dota2.gamepedia.com/Nature%27s_Prophet";
		startCrawling (url);
	}
}
