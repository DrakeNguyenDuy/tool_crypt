package business;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import helppers.Constants;

public class RSA {
	private Cipher cipher;
	private static RSA rsa = null;

	public RSA() {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public static RSA getInstance() {
		if (rsa == null) {
			rsa = new RSA();
		}
		return rsa;
	}

	public String encrypt(byte[] byteText, String keyType, Object... objects)
			throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
		switch (keyType) {
		case Constants.PRIVATE_KEY:
			cipher.init(Cipher.ENCRYPT_MODE, (PrivateKey) objects[0]);
			break;
		default:
			cipher.init(Cipher.ENCRYPT_MODE, (PublicKey) objects[0]);
			break;
		}
		byte[] byteResult = cipher.doFinal(byteText);
		System.out.println("Mã hóa: " + Base64.getEncoder().encodeToString(byteResult) + "==");
		return Base64.getEncoder().encodeToString(byteResult);

	}

	public byte[] decrypt(String byteText, String keyType, Object... objects)
			throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
		switch (keyType) {
		case Constants.PUBLIC_KEY:
			cipher.init(Cipher.DECRYPT_MODE, (PublicKey) objects[0]);
			break;
		default:
			cipher.init(Cipher.DECRYPT_MODE, (PrivateKey) objects[0]);
			break;
		}
		byte[] temp = Base64.getDecoder().decode(byteText);
		byte[] byteResult = cipher.doFinal(temp);
		System.out.println("Sau Mã hóa: " + new String(byteResult));
//			return Base64.getEncoder().encodeToString(byteResult);
		return byteResult;
	}

	public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
		RSA bdx = new RSA();
		RSAKey key = new RSAKey();
		key.createKey(2048);
		String s1 = "ddd";
		String e= bdx.encrypt(s1.getBytes(), Constants.PUBLIC_KEY, key.getPublicKey());
		bdx.decrypt(e, Constants.PRIVATE_KEY, key.getPrivateKey());
	}
}
