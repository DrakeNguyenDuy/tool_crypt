package business.criptCeasar;

import helppers.Templates;

public class Encript {
    public static String encript(String text, int key) {
        String result = "";
        String s = Templates.template;
        char[] template = s.toCharArray();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = s.indexOf(Character.toString(chars[i])) + key;
            if (Character.toString(chars[i]).equals(" ")) {
                result += " ";
            } else if (index < template.length) {
                result += Character.toString(template[index]);
            } else {
                index = index % template.length;
                result += Character.toString(template[index]);
            }
        }
        System.out.println(result);
        return result;
    }
}
