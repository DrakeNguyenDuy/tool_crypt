package business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
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

	// instance of RSA
	public static RSA getInstance() {
		if (rsa == null) {
			rsa = new RSA();
		}
		return rsa;
	}

	/*
	 * method encrypt with input: data input, type of key, key output: a string
	 */
	public String encrypt(byte[] byteText, Object object)
			throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
		int keySize;
		// determine what is type key
		if (object instanceof PublicKey) {
			PublicKey publicKey = (PublicKey) object;
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;//cast to RSAPublickey to get bit length of key
			keySize = rsaPublicKey.getModulus().bitLength();
		} else {
			PrivateKey privateKey = (PrivateKey) object;
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;//cast to RSAPrivatekey to get bit length of key
			keySize = rsaPrivateKey.getModulus().bitLength();
		}
		// read all byte
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteText);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffered = generateByteBuffer(Constants.ENCRYPT, keySize);
		int byteRead = 0;
		try {
			while ((byteRead = byteArrayInputStream.read(buffered)) != -1) {
				byteArrayOutputStream.write(cipher.doFinal(buffered));
			}
			byteArrayOutputStream.flush();
		} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
	}

	/*
	 * method decrypt with input: data input, type of key output string: a string
	 */

	public String decrypt(String byteText, Object object)
			throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
		int keySize;
		// determine what is type key
		if (object instanceof PublicKey) {
			PublicKey publicKey = (PublicKey) object;
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;//cast to RSAPublickey to get bit length of key
			keySize = rsaPublicKey.getModulus().bitLength();
		} else {
			PrivateKey privateKey = (PrivateKey) object;
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;//cast to RSAPrivatekey to get bit length of key
			keySize = rsaPrivateKey.getModulus().bitLength();
		}
		// decode base64 string to byte array
		byte[] temp = Base64.getDecoder().decode(byteText);
		// read all byte
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(temp);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffered = generateByteBuffer(Constants.DECRYPT, keySize);
		try {
			while (byteArrayInputStream.read(buffered) != -1) {
				byteArrayOutputStream.write(cipher.doFinal(buffered));
			}
			return new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
		} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/*
	 * - this method generate byte buffer to read - With mode encrypt then create a
	 * - byte array: + key size 1024 then byte array size is 128 -11 = 117 (padding)
	 * + key size 2048 then byte array size is 256 - 11 = 245 (padding) - With mode
	 * - decrypt then create a byte array: + key size 1024 then byte array size is
	 * 128 + key size 2048 then byte array size is 256
	 */
	public byte[] generateByteBuffer(String typeModeCrypt, int keySize) {
		if (typeModeCrypt.equals(Constants.ENCRYPT)) {
			return new byte[keySize == 1024 ? 117 : 245];
		} else {
			return new byte[keySize == 1024 ? 128 : 256];
		}
	}

//	public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, ClassCastException, BadPaddingException {
//		RSA bdx = new RSA();
//		RSAKey key = new RSAKey();
//		key.createKey(2048);
//		String s1 = "ddd";
//		String e= bdx.encrypt(s1.getBytes(), Constants.PUBLIC_KEY, key.getPublicKey());
//		bdx.decrypt(e, Constants.PRIVATE_KEY, key.getPrivateKey());
//	}
}
