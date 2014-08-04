package phu.quang.le.SimpleVisitor;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.NodeVisitor;

import phu.quang.le.Dao.Skill;

public class AbilityVisitor extends NodeVisitor {

	public boolean shouldGetSkillInfo = false;
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
				if (notes != null) {
					System.out.println (notes.size ());
				}
				System.out.println ("Created new Skill");
				skill = new Skill ();
				notes = new ArrayList<> ();
			}
		}
		if (tag instanceof TableColumn) {
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
				if (tc.getAttribute ("style") != null
						&& tc.getAttribute ("style").equals (
								"width:100%; padding-top: 2px;")) {
					System.out.println (ParserUtils.trimAllTags (
							tc.getStringText (), false));
				}
				if (tc.getAttribute ("style") != null
						&& tc.getAttribute ("style").equals (
								"vertical-align:top; padding:3px 5px;")) {
					if (tc.getAttribute ("colspan") != null
							&& tc.getAttribute ("colspan").equals ("2")) {
						System.out.println (ParserUtils.trimAllTags (tc
								.getStringText ().replaceAll ("<br />", "\n"),
								false));
					} else {
						System.out.println (ParserUtils.trimAllTags (
								tc.getStringText (), false));
					}

				}
			}
		} else if (tag instanceof Bullet) {
			Bullet item = (Bullet) tag;
			text.append (ParserUtils.trimAllTags (item.getStringText (), false));
			notes.add (text);
			text = new StringBuffer (100);
		} else if (tag instanceof LinkTag) {
			if (shouldGetSkillInfo) {
				LinkTag lt = (LinkTag) tag;
				// System.out.println (lt.extractLink ());
			}
		}
	}

	@Override
	public void finishedParsing () {
		if (notes != null) {
			System.out.println (notes.size ());
		}
		super.finishedParsing ();
	}
}
