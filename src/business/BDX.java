package business;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class BDX {
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private Cipher cipher;
	public static final String PUBLIC_KEY = "PUBLIC_KEY";
	public static final String PRIVATE_KEY = "PRIVATE_KEY";

	public BDX() {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createKey() {
		try {
			KeyPairGenerator generatorKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			generatorKeyPairGenerator.initialize(2048);
			keyPair = generatorKeyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public byte[] encrypt(String text, String keyType) {
		try {
			switch (keyType) {
			case PRIVATE_KEY:
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);
				break;

			default:
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				break;
			}
			byte[] byteText = text.getBytes();
			byte[] byteResult = cipher.doFinal(byteText);
			System.out.println("Mã hóa: " + new String(byteResult));
			return byteResult;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return null;
		} catch (BadPaddingException e) {
			return null;
		} catch (IllegalBlockSizeException e) {
			return null;
		}
	}

	public void decrypt(byte[] t, String keyType) {
		try {
			switch (keyType) {
			case PUBLIC_KEY:
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
				break;
			default:
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				break;
			}
			byte[] byteText = t;
			byte[] byteResult = cipher.doFinal(byteText);
			System.out.println("Sau khi mã hóa " + new String(byteResult));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		}
	}

	public static void main(String[] args) {
		BDX bdx = new BDX();
		bdx.createKey();
		byte[] e = bdx.encrypt("dai hoc nong lam", PRIVATE_KEY);
		bdx.decrypt(e, PUBLIC_KEY);
	}
}
