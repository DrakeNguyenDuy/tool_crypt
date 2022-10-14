package business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DES {
	private Cipher cipher;
	private SecretKey secretKey;
	private static DES des = null;

	public DES() {
		try {
			this.cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public static DES getInstance() {
		if (des == null) {
			des = new DES();
			return des;
		}
		return des;
	}

	public SecretKey createKey() {
		KeyGenerator generator;
		try {
			generator = KeyGenerator.getInstance("DES");
			generator.init(56);
			secretKey = generator.generateKey();
			return secretKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveKey(String path) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(new File(path)));
			objectOutputStream.writeObject(createKey());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadKey() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new FileInputStream(new File("D:\\HKI-NAM4\\ATBMTT\\test attbm\\long.txt")));
			try {
				secretKey = (SecretKey) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decryptFile(String inPath, String outPath) throws InvalidKeyException, IOException {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		File in = new File(inPath);
		File out = new File(outPath);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(out));
		CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(in), cipher);
		byte[] byteRead = new byte[1024];
		while (cipherInputStream.read(byteRead) != -1) {
			bufferedOutputStream.write(byteRead);
		}
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		cipherInputStream.close();
		System.out.println("Success");
	}

	public void encryptFile(String inPath, String outPath) throws InvalidKeyException, IOException {
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		File out = new File(outPath);
		File in = new File(inPath);
		out.createNewFile();
		out.setReadable(true);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(in));
		CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(out), cipher);
		byte[] byteRead = new byte[1024];
		while (bufferedInputStream.read(byteRead) != -1) {
			cipherOutputStream.write(byteRead);
		}
		cipherOutputStream.flush();
		cipherOutputStream.close();
		bufferedInputStream.close();
		System.out.println("thanh cong");
	}

	public static void main(String[] args)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		DES des = new DES();
		des.createKey();
//		des.saveKey();
		des.loadKey();
//		des.encrypt("D:\\newfolder\\ooo.png", "D:\\newfolder\\encrypt.png");
//		des.decrypt("D:\\newfolder\\encrypt.png", "D:\\newfolder\\decrypt.png");
	}
}
