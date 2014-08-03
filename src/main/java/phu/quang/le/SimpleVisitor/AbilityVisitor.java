package phu.quang.le.SimpleVisitor;

import org.htmlparser.Tag;
import org.htmlparser.visitors.NodeVisitor;

public class AbilityVisitor extends NodeVisitor {

	@Override
	public void visitTag (Tag tag) {
		System.out.println (tag.getTagName ());
	}

	public void adasd () {

	}
}
