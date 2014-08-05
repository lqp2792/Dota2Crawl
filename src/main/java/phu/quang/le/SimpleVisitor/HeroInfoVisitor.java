package phu.quang.le.SimpleVisitor;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableHeader;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.NodeVisitor;

import phu.quang.le.Dao.Hero;
import phu.quang.le.Dao.HeroStat;
import phu.quang.le.Dao.LevelStat;
import phu.quang.le.Dao.SummonStat;
import phu.quang.le.Util.DBUtility;
import phu.quang.le.Util.StringUtils;

public class HeroInfoVisitor extends NodeVisitor {

	public Hero hero;
	public HeroStat heroStat;
	public SummonStat summonStat;
	public List<LevelStat> levelStats;
	public int infoboxNum = 0;

	public HeroInfoVisitor (HeroStat heroStat, Hero hero, List<LevelStat> levelStats) {
		this.heroStat = heroStat;
		this.hero = hero;
		this.levelStats = levelStats;
	}

	// If this hero can summon pet
	public HeroInfoVisitor (HeroStat heroStat, Hero hero, List<LevelStat> levelStats,
			SummonStat summonStat) {
		this.heroStat = heroStat;
		this.hero = hero;
		this.levelStats = levelStats;
		this.summonStat = summonStat;
	}

	@Override
	public void visitTag (Tag tag) {
		if (tag instanceof TableTag) {
			if (tag.getAttribute ("class") != null
					&& tag.getAttribute ("class").equals ("infobox")) {
				infoboxNum++;
			}
		} else if (tag instanceof TableRow) {
			processTableRow (tag);
		} else if (tag instanceof ImageTag) {
			processImageTag (tag);
		} else if (tag instanceof TableHeader) {
			processTableHeader (tag);
		}
	}

	/**
	 * If input Tag is tr, then get all table column of this row. If this hero
	 * can summon unit, we will count infobox number to know when we get hero
	 * information, when we get summon unit information
	 * 
	 * @param tag
	 *            Table Row tag tr
	 */
	public void processTableRow (Tag tag) {
		TableRow tr = (TableRow) tag;
		TableColumn[] tcs = tr.getColumns ();
		int level = 0;
		LevelStat levelStat = null;
		String prevText = null;
		String presentText = null;
		StringTokenizer tokens = null;
		for (TableColumn tc : tcs) {
			presentText = ParserUtils.trimAllTags (tc.getStringText (), false);
			presentText = StringUtils.rtrim (StringUtils.ltrim (presentText));
			if (prevText != null) {
				if (infoboxNum == 1) {
					levelStat = levelStats.get (level);
					switch (prevText) {
					case "Movement Speed" :
						System.out.println ("Set Movement Speed: " + presentText);
						heroStat.setMoveSpeed (Integer.parseInt (presentText));
						break;
					case "Turn Rate" :
						System.out.println ("Set Turn rate: " + presentText);
						heroStat.setTurnRate (Double.parseDouble (presentText));
						break;
					case "Sight Range" :
						System.out.println ("Set Sight range: " + presentText);
						tokens = new StringTokenizer (presentText, "/");
						if (tokens.hasMoreTokens ()) {
							heroStat.setDaySightRange (Integer.parseInt (tokens
									.nextToken ()));
							heroStat.setNightSightRange (Integer.parseInt (tokens
									.nextToken ()));
						}
						break;
					case "Attack Range" :
						System.out.println ("Set Attack range: " + presentText);
						heroStat.setAttackRange (presentText);
						break;
					case "Missile Speed" :
						System.out.println ("Set Missile Speed: " + presentText);
						heroStat.setMissileSpeed (presentText);
						break;
					case "Attack Duration" :
						System.out.println ("Set Attack Duration: " + presentText);
						tokens = new StringTokenizer (presentText, "+");
						if (tokens.hasMoreTokens ()) {
							heroStat.setAttackPoint (Double.parseDouble (tokens
									.nextToken ()));
							heroStat.setAttackBackswing (Double.parseDouble (tokens
									.nextToken ()));
						}
						break;
					case "Cast Duration" :
						System.out.println ("Set Cast Duration: " + presentText);
						tokens = new StringTokenizer (presentText, "+");
						if (tokens.hasMoreTokens ()) {
							heroStat.setCastPoint (Double.parseDouble (tokens
									.nextToken ()));
							heroStat.setCastBackswing (Double.parseDouble (tokens
									.nextToken ()));
						}
						break;
					case "Base Attack Time" :
						System.out.println ("Set Base Attack Time: " + presentText);
						heroStat.setBaseAttackTime (Double.parseDouble (presentText));
						break;
					case "Hit Points" :
						levelStat.setHitPoints (Integer.parseInt (presentText));
						level++;
						System.out.println ("Set Hit points at level: "
								+ levelStat.getLevel () + " is "
								+ levelStat.getHitPoints ());
						break;
					case "Mana" :
						levelStat.setMana (Integer.parseInt (presentText));
						level++;
						System.out.println ("Set Mana at level: " + levelStat.getLevel ()
								+ " is " + levelStat.getMana ());
						break;
					case "Damage" :
						tokens = new StringTokenizer (presentText, "‒");
						if (tokens.countTokens () != 2) {
							tokens = new StringTokenizer (presentText, "-");
						}
						if (tokens.hasMoreTokens ()) {
							levelStat.setStartDamage (Integer.parseInt (tokens
									.nextToken ()));
							levelStat
									.setEndDamage (Integer.parseInt (tokens.nextToken ()));
						}
						level++;
						System.out.println ("Set Damage at level: "
								+ levelStat.getLevel () + " is "
								+ levelStat.getStartDamage () + "-"
								+ levelStat.getEndDamage ());
						break;
					case "Armor" :
						levelStat.setArmor (Double.parseDouble (presentText));
						level++;
						System.out.println ("Set Armor at level: "
								+ levelStat.getLevel () + " is " + levelStat.getArmor ());
						break;
					case "Attacks / Second" :
						levelStat.setAttackSpeed (Double.parseDouble (presentText));
						level++;
						System.out.println ("Set Attacks / Second at level: "
								+ levelStat.getLevel () + " is "
								+ levelStat.getAttackSpeed ());
						break;
					}
				}
				if (infoboxNum == 2) {
				}
			}
			if (level == 0) {
				prevText = presentText;
			}
			if (level == 3) {
				level = 0;
			}
		}
	}

	public void processImageTag (Tag tag) {
		ImageTag it = (ImageTag) tag;
		if (it.getAttribute ("alt").equals (hero.getName () + ".png")) {
			hero.setImgUrl (it.getImageURL ());
			System.out.println ("Set Hero image URL: " + hero.getImgUrl ());
		} else if (it.getAttribute ("width").equals ("256")
				&& it.getAttribute ("height").equals ("144")) {
		}
	}

	public void processTableHeader (Tag tag) {
		TableHeader th = (TableHeader) tag;
		String presentText = null;
		String stat = null;
		StringTokenizer tokens = null;
		try {
			Parser parser = ParserUtils.createParserParsingAnInputString (th
					.getStringText ());
			NodeList imgNodes = parser.parse (new NodeClassFilter (ImageTag.class));
			if (imgNodes.size () != 0) {
				ImageTag it = (ImageTag) imgNodes.elementAt (0);
				presentText = it.getAttribute ("alt");
				stat = ParserUtils.trimAllTags (th.getStringText (), false);
				tokens = new StringTokenizer (stat, "+ ");
				switch (presentText) {
				case "Strength" :
					heroStat.setBeginStrength (Double.parseDouble (tokens.nextToken ()));
					heroStat.setStrPerLevel (Double.parseDouble (tokens.nextToken ()));
					System.out.println ("Set Beginning Strength: "
							+ heroStat.getBeginStrength ());
					System.out.println ("Set Strength per level: "
							+ heroStat.getStrPerLevel ());
					break;
				case "Agility" :
					heroStat.setBeginAgility (Double.parseDouble (tokens.nextToken ()));
					heroStat.setAgiPerLevel (Double.parseDouble (tokens.nextToken ()));
					System.out.println ("Set Beginning Agility: "
							+ heroStat.getBeginAgility ());
					System.out.println ("Set Agility per level: "
							+ heroStat.getAgiPerLevel ());
					break;
				case "Intelligence" :
					heroStat.setBeginIntel (Double.parseDouble (tokens.nextToken ()));
					heroStat.setIntPerLevel (Double.parseDouble (tokens.nextToken ()));
					System.out.println ("Set Beginning Intelligence: "
							+ heroStat.getBeginIntel ());
					System.out.println ("Set Intelligence per level: "
							+ heroStat.getIntPerLevel ());
					break;
				}
			} else {
				parser = ParserUtils.createParserParsingAnInputString (th
						.getStringText ());
				NodeList spanNodes = parser.parse (new NodeClassFilter (Span.class));
				if (spanNodes.size () != 0) {
					String level = ParserUtils.trimAllTags (th.getStringText (), false);
					LevelStat levelStat = new LevelStat ();
					levelStat.setLevel (Integer.parseInt (level.trim ()));
					levelStats.add (levelStat);
					System.out.println ("Set Level Stat: " + levelStat.getLevel ());
				} else {
					if (hero.getName () == null) {
						hero.setName (th.getStringText ().trim ());
						System.out.println ("Set Hero Name: " + hero.getName ());
					}
				}
			}
		} catch (ParserException | UnsupportedEncodingException e) {
			System.err.println (e);
		}
	}

	@Override
	public void finishedParsing () {
		hero.setHeroStat (heroStat);
		DBUtility.addHero (hero);
		super.finishedParsing ();
	}
}
