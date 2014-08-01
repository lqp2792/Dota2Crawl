package phu.quang.le.SimpleVisitor;

import java.util.StringTokenizer;

import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.visitors.NodeVisitor;
import org.jsoup.Jsoup;

import phu.quang.le.Dao.HeroStat;

public class SimpleVisitor extends NodeVisitor {
	public HeroStat heroStat;
	
	public SimpleVisitor(HeroStat heroStat) {
		this.heroStat = heroStat;
	}
	
	@Override
	public void visitTag(Tag tag) {
		if (tag instanceof TableRow) {
			TableRow tr = (TableRow) tag;
			TableColumn[] tcs = tr.getColumns();
			String prevText = null;
			String presentText = null;
			StringTokenizer tokens = null;
			for(TableColumn tc : tcs) {
				presentText = Jsoup.parse(tc.getStringText()).text();
				if(prevText != null) {
					switch(prevText) {
					case "Movement Speed":
						System.out.println("Set Movement Speed: " + presentText);
						heroStat.setMoveSpeed(Integer.parseInt(presentText));
						break;
					case "Turn Rate":
						System.out.println("Set Turn rate: " + presentText);
						heroStat.setTurnRate(Double.parseDouble(presentText));
						break;
					case "Sight Range":
						System.out.println("Set Sight range: " + presentText);
						tokens = new StringTokenizer(presentText, "/");
						if(tokens.hasMoreTokens()) {
							heroStat.setDaySightRange(Integer.parseInt(tokens.nextToken()));
							heroStat.setNightSightRange(Integer.parseInt(tokens.nextToken()));
						}
						break;
					case "Attack Range":
						System.out.println("Set Attack range: " + presentText);
						heroStat.setMissileSpeed(presentText);
						break;
					case "Missile Speed":
						System.out.println("Set Missile Speed: " + presentText);
						heroStat.setMissileSpeed(presentText);
						break;
					case "Attack Duration":
						System.out.println("Set Attack Duration: " + presentText);
						tokens = new StringTokenizer(presentText, "+");
						if(tokens.hasMoreTokens()) {
							heroStat.setAttackPoint(Double.parseDouble(tokens.nextToken()));
							heroStat.setAttackBackswing(Double.parseDouble(tokens.nextToken()));
						}
						break;
					case "Cast Duration": 
						System.out.println("Set Cast Duration: " + presentText);
						tokens = new StringTokenizer(presentText, "+");
						if(tokens.hasMoreTokens()) {
							heroStat.setCastPoint(Double.parseDouble(tokens.nextToken()));
							heroStat.setCastBackswing(Double.parseDouble(tokens.nextToken()));
						}
						break;
					case "Base Attack Time": 
						System.out.println("Set Base Attack Time: " + presentText);
						heroStat.setBaseAttackTime(Double.parseDouble(presentText));
						break;
					}
				}
				prevText = presentText;
			}
		} else if (tag instanceof LinkTag) {
			LinkTag lt = (LinkTag) tag;
			System.out.println(Jsoup.parse(lt.getStringText()).text());
		}
	}
	
	@Override
	public void visitStringNode(Text string) {
//		System.out.println(string.getText());
	}
}
