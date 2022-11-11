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

import helppers.Constants;

public class Hashing {
	private List<String> storeHasing;// list contain hashing code
	private List<String> storePt;// list contain plain text
	private MessageDigest hashing;

	public Hashing() {
		this.storeHasing = new ArrayList<>();
	}

	/*
	 * encrypt plain text to hashing code input: + typeHashing: Type hashing +
	 * plaintText: data input + append: is save in store output: hashing code
	 */
	public String encrypt(String typeHasing, byte[] plainText, boolean append) {
		try {
			this.hashing = MessageDigest.getInstance(typeHasing);
			this.hashing.update(plainText);
			byte[] digest = this.hashing.digest();
			BigInteger bigInteger = new BigInteger(1, digest);
			String hash = bigInteger.toString(16);// hex value
			if (append) {
				String pathStoreHashing = null;
				String pathStorePlaintext = null;
				/*
				 * get path of store hashing code and path of store plain text
				 */
				switch (typeHasing) {
				case Constants.MD5:
					pathStoreHashing = Constants.pathHashMD5;
					pathStorePlaintext = Constants.pathPlTextMD5;
					break;
				case Constants.SHA1:
					pathStoreHashing = Constants.pathHashSHA1;
					pathStorePlaintext = Constants.pathPlTextSHA1;
					break;
				case Constants.SHA256:
					pathStoreHashing = Constants.pathHashSHA256;
					pathStorePlaintext = Constants.pathPlTextSHA256;
					break;
				case Constants.SHA512:
					pathStoreHashing = Constants.pathHashSHA512;
					pathStorePlaintext = Constants.pathPlTextSHA512;
					break;
				default:
					break;
				}
				addHashToStore(pathStoreHashing, hash);
				addPTToStore(pathStorePlaintext, new String(plainText));
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	// add hashing code to store hashing
	public void addHashToStore(String pathStoreHash, String newHash) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(pathStoreHash), true));
			CharSequence charSequence = new String(newHash);
			bufferedWriter.append(charSequence);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add plaint text to store plaint text
	public void addPTToStore(String pathPlTextStore, String pt) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(pathPlTextStore), true));
			CharSequence charSequence = new String(pt);
			bufferedWriter.append(charSequence);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// load hashing from store
	public void loadHashing(String typeHashing) {
		try {
			String path = null;
			// get path by type hashing
			switch (typeHashing) {
			case Constants.MD5:
				path = Constants.pathHashMD5;
				break;
			case Constants.SHA1:
				path = Constants.pathHashSHA1;
				break;
			case Constants.SHA256:
				path = Constants.pathHashSHA256;
				break;
			case Constants.SHA512:
				path = Constants.pathHashSHA512;
				break;
			default:
				break;
			}
			// load hashing
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
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

	public void loadPT(String typeHashing) throws IOException {
		try {
			String path = null;
			// get path store plant text
			switch (typeHashing) {
			case Constants.MD5:
				path = Constants.pathPlTextMD5;
				break;
			case Constants.SHA1:
				path = Constants.pathPlTextSHA1;
				break;
			case Constants.SHA256:
				path = Constants.pathPlTextSHA256;
				break;
			case Constants.SHA512:
				path = Constants.pathPlTextSHA512;
				break;
			default:
				break;
			}
			// load plant text
			this.storePt = new ArrayList<>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
			Stream<String> lines = bufferedReader.lines();
			lines.distinct().forEach(item -> this.storePt.add(item));
			lines.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// decrypt is load hashing and plait text stored
	public String decrypt(String hashingCode, String typeHashing) {
		// load all hashing stored
		loadHashing(typeHashing);
		// check is contain hashing code finding
		if (storeHasing.contains(hashingCode)) {
			try {
				// load plain text by hashing code
				loadPT(typeHashing);
				int index = storeHasing.indexOf(hashingCode);
				String pt = this.storePt.get(index);
				return pt;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		// not found hashing code
		return "Not found";
	}
}
