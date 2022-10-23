package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import helppers.Constants;

public class RSAKey {
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private static RSAKey createKey = null;

	public static RSAKey getInstance() {
		if (createKey == null) {
			createKey = new RSAKey();
		}
		return createKey;
	}

	public void createKey(int keySize) {
		try {
			KeyPairGenerator generatorKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			generatorKeyPairGenerator.initialize(keySize);
			keyPair = generatorKeyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void saveKey(String path, String type) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
			if (type.equals(Constants.PRIVATE_KEY)) {
				objectOutputStream.writeObject(privateKey);
			} else {
				objectOutputStream.writeObject(publicKey);
			}
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] loadKey(String path) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path)));
			Object object = objectInputStream.readObject();
			if (object instanceof PrivateKey) {
				privateKey = (PrivateKey) object;
				objectInputStream.close();
				return privateKey.getEncoded();
			} else {
				publicKey = (PublicKey) object;
				objectInputStream.close();
				return publicKey.getEncoded();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
