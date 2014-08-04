package phu.quang.le.SimpleVisitor;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableHeader;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.NodeVisitor;

import phu.quang.le.Dao.Skill;
import phu.quang.le.Util.StringUtils;

public class AbilityVisitor extends NodeVisitor {

	public int count = 0;
	public int printCount = 0;
	public boolean shouldGetSkillInfo = false;
	public boolean shouldGetSkillType = false;
	public List<Skill> skills;
	public List<StringBuffer> notes;
	public Skill skill;
	public StringBuffer text = new StringBuffer (100);

	public AbilityVisitor (List<Skill> skills) {

		this.skills = skills;
	}

	@Override
	public void visitTag (Tag tag) {

		if (tag instanceof TableTag) {
			if (tag.getAttribute ("style").equals (
					"border:0;padding:0;margin:0;margin-bottom:1em;")) {
				System.out.println ("========================================");
				skill = new Skill ();
				notes = new ArrayList<> ();
			}
			if (tag.getAttribute ("style")
					.equals (
							"width:100%;font-size:85%;line-height:100%;border-top:0px solid black;border-bottom:1px solid black;text-align:center;")) {
				shouldGetSkillType = true;
			}
		} else if (tag instanceof TableHeader) {
			if (tag.getAttribute ("style") != null) {
				String val = tag.getAttribute ("style");
				if(val.equals ("width:30%;") || val.equals ("width:30%")) {
					count++;
				}
			}
		} else if (tag instanceof TableColumn) {
			if (tag.getAttribute ("rowspan") != null
					&& tag.getAttribute ("rowspan").equals ("2")) {
				shouldGetSkillInfo = true;
			}
			if (tag.getAttribute ("style") != null
					&& tag.getAttribute ("style")
							.equals (
									"vertical-align:top;border-left:1px solid black;padding:3px 5px;")) {
				shouldGetSkillInfo = false;
			}
			if (shouldGetSkillInfo) {
				TableColumn tc = (TableColumn) tag;
				if (!shouldGetSkillType) {
					if (tc.getAttribute ("style") != null
							&& tc.getAttribute ("style").equals (
									"width:100%; padding-top: 2px;")) {
						System.out.println (ParserUtils.trimAllTags (tc.getStringText (),
								false));
					}
					if (tc.getAttribute ("style") != null
							&& tc.getAttribute ("style").equals (
									"vertical-align:top; padding:3px 5px;")) {
						if (tc.getAttribute ("colspan") != null
								&& tc.getAttribute ("colspan").equals ("2")) {
							System.out.println (StringUtils.ltrim (ParserUtils
									.trimAllTags (
											tc.getStringText ().replaceAll ("<br />",
													"\n"), false)));
						} else {
							System.out.println (StringUtils.ltrim (ParserUtils
									.trimAllTags (tc.getStringText (), false)));
						}
					}
				} else {
					System.out.println ();
					switch(printCount) {
					case 0:
						System.out.print("Ability: " + StringUtils.ltrim (ParserUtils.trimAllTags (
								tc.getStringText (), false)));
						break;
					case 1:
						System.out.print("Affects: " + StringUtils.ltrim (ParserUtils.trimAllTags (
								tc.getStringText (), false)));
						break;
					case 2:
						System.out.print("Damage: " + StringUtils.ltrim (ParserUtils.trimAllTags (
								tc.getStringText (), false)));
						break;
					}
					printCount++;
					if (printCount == count) {
						count = 0;
						printCount = 0;
						shouldGetSkillType = false;
					}
				}
			}
		} else if (tag instanceof Bullet) {
			Bullet item = (Bullet) tag;
			text.append (ParserUtils.trimAllTags (item.getStringText (), false));
			notes.add (text);
			text = new StringBuffer (100);
		} else if (tag instanceof LinkTag) {
			processLinkTag (tag, shouldGetSkillInfo);
		} else if (tag instanceof ImageTag) {
			processImageTag (tag, shouldGetSkillInfo);
		}
	}

	@Override
	public void finishedParsing () {

		super.finishedParsing ();
	}

	/**
	 * Process link tag, <a with href>
	 * 
	 * @param shouldGetSkillInfo
	 *            need to get information or not
	 */
	public void processLinkTag (Tag tag, boolean shouldGetSkillInfo) {

		if (shouldGetSkillInfo) {
			LinkTag lt = (LinkTag) tag;
			TableColumn tableColumn = null;
			if (lt.getAttribute ("title") != null) {
				switch (lt.getAttribute ("title")) {
				case "Cooldown":
					tableColumn = (TableColumn) lt.getParent ();
					System.out.println (ParserUtils.trimAllTags (
							tableColumn.getStringText (), false).trim ());
					break;
				case "Mana":
					tableColumn = (TableColumn) lt.getParent ();
					System.out.println (ParserUtils.trimAllTags (
							tableColumn.getStringText (), false).trim ());
					break;
				default:
					if (lt.getParent () instanceof TableColumn) {
						tableColumn = (TableColumn) lt.getParent ();
						if (tableColumn.getAttribute ("style") != null
								&& tableColumn.getAttribute ("style").equals (
										"width:100%;padding-right: 100px;")) {
							System.out.println (ParserUtils.trimAllTags (
									tableColumn.getStringText (), false).trim ());
						}
					}
				} // end switch
			}
		}
	}

	/**
	 * 
	 * @param tag
	 * @param shouldGetSkillInfo
	 */
	public void processImageTag (Tag tag, boolean shouldGetSkillInfo) {

		if (shouldGetSkillInfo) {
			ImageTag it = (ImageTag) tag;
			if (it.getParent () instanceof LinkTag) {
				LinkTag linkParent = (LinkTag) it.getParent ();
				if (linkParent.getAttribute ("class") != null
						&& linkParent.getAttribute ("class").equals ("image")) {
					System.out.println ("Skill Name: "
							+ it.getAttribute ("alt").replaceAll (" icon.png", ""));
					System.out.println ("Skill Image URL: " + it.getImageURL ());
				}
			}
		}
	}
}
