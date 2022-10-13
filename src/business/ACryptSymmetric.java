package business;

import helppers.Templates;

public abstract class ACryptSymmetric {
    public static String moveSingleCharEncrypt(int index, char charCurrent) {
//        String s = TemplateContanst.templateEn.toUpperCase();
        String s = Templates.templateEN;
        char[] chars = s.toCharArray();
        if (s.indexOf(Character.toString(charCurrent)) < 0) {
            return Character.toString(charCurrent);
        } else if (index < s.length()) {
            return Character.toString(chars[index]);
        } else {
            index = index % s.length();
            return Character.toString(chars[index]);
        }
    }

    public static String moveSingleCharDecrypt(int index, char charCurrent) {
        String s = Templates.templateEN;
        char[] chars = s.toCharArray();
        if (s.indexOf(Character.toString(charCurrent)) < 0) {
            return Character.toString(charCurrent);
        } else if (index >= 0) {
            return Character.toString(chars[index]);
        } else {
            index = index + s.length();
            return Character.toString(chars[index]);
        }
    }
}
