package phu.quang.le.Util;

public class StringUtils {

	public static String ltrim (String s) {

		int i = 0;
		while (i < s.length () && Character.isWhitespace (s.charAt (i))) {
			i++;
		}
		return s.substring (i);
	}
}
