package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MD5 {
	private List<String> storeHasing;
	private List<String> storePt;
	private static MD5 md5 = null;

	public MD5() {
		this.storeHasing = new ArrayList<>();
	}

	public static MD5 getInstance() {
		if (md5 == null) {
			md5 = new MD5();
		}
		return md5;
	}

	public String encrypt(byte[] plainText, boolean append) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(plainText);
			byte[] digest = md5.digest();
			BigInteger bigInteger = new BigInteger(1, digest);
			String hash = bigInteger.toString(16);// hex value
			if (append) {
				addHashToStore(hash);
				addPTToStore(new String(plainText));
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addHashToStore(String newHash) {
		try {
//		RandomAccessFile randomAccessFile = new RandomAccessFile("src/store/storemd5.txt", "rwd");
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(new File("src/store/storemd5_hash.txt"), true));
			CharSequence charSequence = new String(newHash);
			bufferedWriter.append(charSequence);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addPTToStore(String pt) {
		try {
//		RandomAccessFile randomAccessFile = new RandomAccessFile("src/store/storemd5.txt", "rwd");
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(new File("src/store/storemd5_pt.txt"), true));
			CharSequence charSequence = new String(pt);
			bufferedWriter.append(charSequence);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadHashing() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/store/storemd5_hash.txt")));
			Stream<String> lines = bufferedReader.lines();
			lines.distinct().forEach(item -> this.storeHasing.add(item));
			lines.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPT() throws IOException {
		try {
			this.storePt = new ArrayList<>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/store/storemd5_pt.txt")));
			Stream<String> lines = bufferedReader.lines();
			lines.distinct().forEach(item -> this.storePt.add(item));
			lines.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String decrypt(String hashingCode) {
		loadHashing();
		if (storeHasing.contains(hashingCode)) {
			try {
				loadPT();
				int index = storeHasing.indexOf(hashingCode);
				String pt = this.storePt.get(index);
				return pt;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return "Not found";
	}

//	public static void main(String[] args) {
//		MD5 md5 = new MD5();
////		md5.encrypt("nguyễn dũy long".getBytes(), true);
//		md5.decrypt("f2ad554d9717c71a20f3351391af30f1");
//	}
}
