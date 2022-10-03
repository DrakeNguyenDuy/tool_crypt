package business;

import helppers.Templates;

public class CriptCeasar {
    public String encript(String text, int key) {
        String result = "";
        String s = Templates.template;
        char[] template = s.toCharArray();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = s.indexOf(Character.toString(chars[i]));
            if (index < 0) {
                result += Character.toString(chars[i]);
            } else {
                index = index + key;
                if (index < template.length) {
                    result += Character.toString(template[index]);
                } else {
                    index = index % template.length;
                    result += Character.toString(template[index]);
                }
            }
        }
        return result;
    }

    public String decript(String text, int key) {
        String result = "";
        String s = Templates.template;
        char[] template = s.toCharArray();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = s.indexOf(Character.toString(chars[i]));
            if (index < 0) {
                result += Character.toString(chars[i]);
            } else {
                index = index - key;
                if (index < 0) {
                    index = template.length + index;
                    result += Character.toString(template[index]);
                } else {
                    result += Character.toString(template[index]);
                }
            }
        }
        return result;
    }
}
