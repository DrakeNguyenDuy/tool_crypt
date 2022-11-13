package helppers;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class EnToVi {
	public static String translateEn2Vi(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(string).replaceAll("").replaceAll("đ", "d");
	}
}
