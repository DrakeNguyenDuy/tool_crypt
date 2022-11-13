package business;

import helppers.Templates;

public abstract class ACryptSymmetric {
	protected String moveSingleCharEncrypt(int index, String charCurrent) {
//        String s = TemplateContanst.templateEn.toUpperCase();
		String s = Templates.templateEN;
		char[] chars = s.toCharArray();
		System.out.println(charCurrent+":" + s.indexOf(charCurrent));
		if (s.indexOf(charCurrent) < 0) {
			return charCurrent;
		} else if (index < s.length()) {
			return Character.toString(chars[index]);
		} else {
			index = index % s.length();
			return Character.toString(chars[index]);
		}
	}

	protected String moveSingleCharDecrypt(int index, String charCurrent) {
		String s = Templates.templateEN;
		char[] chars = s.toCharArray();
		if (s.indexOf(charCurrent) < 0) {
			return charCurrent;
		} else if (index >= 0) {
			return Character.toString(chars[index]);
		} else {
			index = index + s.length();
			return Character.toString(chars[index]);
		}
	}
}
