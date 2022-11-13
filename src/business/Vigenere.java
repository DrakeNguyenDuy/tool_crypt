package business;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

import helppers.Templates;

public class Vigenere extends ACryptSymmetric {
	private static Vigenere vigenere = null;

	public static Vigenere instance() {
		if (vigenere == null) {
			return vigenere = new Vigenere();
		}
		return vigenere;
	}

	public String encript(String text, String key) {
		char[] charText = text.toUpperCase().toCharArray();// convert to text char array
		char[] charKey = key.toUpperCase().toCharArray();// convert key to char array
		String result = "";
		int countSpace = 0;
		for (int i = 0; i < charText.length; i++) {
			if (charText[i] == ' ') {
				countSpace++;
			}
			int positionCharKey = Templates.templateEN
					.indexOf(Character.toString(charKey[(i - countSpace) % key.length()]));
			int positionCharText = Templates.templateEN.indexOf(Character.toString(charText[i]));// get position char
																									// text in templates
			int index = positionCharText + positionCharKey;// this position intersect between the position text (column)
															// and the position key (row)
			result += super.moveSingleCharEncrypt(index, Character.toString(charText[i]));
		}
		return result;
	}

	public String decrypt(String cypherText, String key) {
		char[] charCiphertext = cypherText.toUpperCase().toCharArray();// convert cipher text to char array
		char[] charKey = key.toUpperCase().toCharArray();// convert key to char array
		String result = "";
		int countSpace = 0;
		for (int i = 0; i < charCiphertext.length; i++) {
			if (charCiphertext[i] == ' ') {
				countSpace++;
			}
			int positionCharKey = Templates.templateEN
					.indexOf(Character.toString(charKey[(i - countSpace) % key.length()]));// get the position of char
																							// key in template
			// get the position of char cipher text in template
			int positionCharCypherText = Templates.templateEN.indexOf(Character.toString(charCiphertext[i]));
			int index = positionCharCypherText - positionCharKey;// this position intersect between cipher text (column)
																	// and the position key (row)
			result += super.moveSingleCharDecrypt(index, Character.toString(charCiphertext[i]));
		}
		return result;
	}

	public String createKey() {
		Random random = new Random();
		String key = "";
		int lengthKey = random.nextInt(100);
		for (int i = 0; i < lengthKey; i++) {
			int index = random.nextInt(26);
			key += Templates.templateEN.substring(index, index + 1);
		}
		return key;
	}
	public static void main(String[] args) {
		String string = Normalizer.normalize("nguyễn đũy long", Normalizer.Form.NFD);
		Pattern pattern= Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		System.out.println(pattern.matcher(string).replaceAll("").replaceAll("đ", "d"));
	}
}
