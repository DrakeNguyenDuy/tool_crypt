package business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.JOptionPane;

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
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
			objectOutputStream.writeObject(secretKey);
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
			secretKey = (SecretKey) objectInputStream.readObject();
			objectInputStream.close();
			return secretKey.getEncoded();
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

	public void decryptFile(String inPath, String outPath) throws InvalidKeyException, IOException {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		File in = new File(inPath);
		File out = new File(outPath);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(out));
		CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(in), cipher);
		byte[] buffered = new byte[1024];
		int byteRead = 0;
		while ((byteRead = cipherInputStream.read(buffered)) != -1) {
			bufferedOutputStream.write(buffered, 0, byteRead);
		}
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		cipherInputStream.close();
	}

	public byte[] decypt(byte[] bytes) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		byteArrayOutputStream.reset();
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(bytes), cipher);
			byte[] buffered = new byte[1024];
			int byteRead = 0;
			while ((byteRead = cipherInputStream.read(buffered)) != -1) {
				byteArrayOutputStream.write(buffered, 0, byteRead);
			}
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Khóa không chính xác");
			return new byte[0];
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return new byte[0];
		}
	}

	public byte[] encrypt(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		byteArrayInputStream.reset();
//		byteArrayOutputStream.reset();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
			byte[] buffered = new byte[1024];
			int byteRead = 0;
			while ((byteRead = byteArrayInputStream.read(buffered)) != -1) {
				cipherOutputStream.write(buffered, 0, byteRead);
			}
			cipherOutputStream.flush();
			cipherOutputStream.close();
			byteArrayInputStream.close();
			byteArrayOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public void encryptFile(String inPath, String outPath) throws InvalidKeyException, IOException {
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		File out = new File(outPath);
		File in = new File(inPath);
		System.out.println(in.length());
		out.createNewFile();
		out.setReadable(true);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(in));
		CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(out), cipher);
		byte[] buffered = new byte[1024];
		int byteRead = 0;
		while ((byteRead = bufferedInputStream.read(buffered)) != -1) {
			cipherOutputStream.write(buffered, 0, byteRead);
		}
		cipherOutputStream.flush();
		cipherOutputStream.close();
		bufferedInputStream.close();
	}

	public byte[] convertObjectToByte() {
		return secretKey.getEncoded();
	}

//	public static void main(String[] args)
//			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
//		DES des = new DES();
////		System.out.println(des.createKey("12345678").getEncoded());;
////		des.saveKey("D:\\HKI-NAM4\\ATBMTT\\test attbm\\long.txt");
////		des.loadKey("D:\\HKI-NAM4\\ATBMTT\\test attbm\\long.txt");
////		des.encryptFile("C:\\Users\\Nguyen Duy Long\\OneDrive\\Tài liệu\\meolon.png", "D:\\newfolder\\encrypt.jpg");
////		des.decryptFile("D:\\newfolder\\encrypt.jpg", "D:\\newfolder\\decrypt.jpg");
//	}
}
