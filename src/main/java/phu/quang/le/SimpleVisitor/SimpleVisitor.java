package phu.quang.le.SimpleVisitor;

import java.util.StringTokenizer;

import org.htmlparser.Tag;
import org.htmlparser.Text;
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
				System.out.println("\n" + tc.getStringText());
				System.out.println(Jsoup.parse(tc.getStringText()).text());
				presentText = Jsoup.parse(tc.getStringText()).text();
				if(prevText != null) {
					switch(prevText) {
					case "Movement Speed":
						heroStat.setMoveSpeed(Integer.parseInt(presentText));
						break;
					case "Turn Rate":
						heroStat.setTurnRate(Double.parseDouble(presentText));
						break;
					case "Sight Range":
						tokens = new StringTokenizer(presentText, "/");
						if(tokens.hasMoreTokens()) {
							heroStat.setDaySightRange(Integer.parseInt(tokens.nextToken()));
							heroStat.setNightSightRange(Integer.parseInt(tokens.nextToken()));
						}
						break;
					case "Attack Range":
						heroStat.setMissileSpeed(presentText);
						break;
					case "Missile Speed":
						heroStat.setMissileSpeed(presentText);
						break;
					case "Attack Duration":
						tokens = new StringTokenizer(presentText, "+");
						if(tokens.hasMoreTokens()) {
							heroStat.setAttackPoint(Double.parseDouble(tokens.nextToken()));
							heroStat.setAttackBackswing(Double.parseDouble(tokens.nextToken()));
						}
						break;
					case "Cast Duration": 
						tokens = new StringTokenizer(presentText, "+");
						if(tokens.hasMoreTokens()) {
							heroStat.setCastPoint(Double.parseDouble(tokens.nextToken()));
							heroStat.setCastBackswing(Double.parseDouble(tokens.nextToken()));
						}
						break;
					case "Base Attack Time": 
						heroStat.setBaseAttackTime(Double.parseDouble(presentText));
						break;
					}
				}
				prevText = presentText;
			}
		}
	}
	
	@Override
	public void visitStringNode(Text string) {
//		System.out.println(string.getText());
	}
}
