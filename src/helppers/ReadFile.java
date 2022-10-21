package helppers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

public class ReadFile {
	private static ReadFile readFile = null;

	public static ReadFile getInstance() {
		if (readFile == null) {
			readFile = new ReadFile();
			return readFile;
		}
		return readFile;
	}

//	public String readFile(String path, JPanel jPanel) {
//		try {
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
//			String textLoad = "";
//			String line;
//			try {
//				while ((line = bufferedReader.read) != null) {
//					textLoad += line;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			bufferedReader.close();
//			return textLoad;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
	public byte[] readFile(String path, JPanel jpannel) {
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(path)));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int byteRead = 0;
			byte[] buffered = new byte[1024];
			while ((byteRead = bufferedInputStream.read(buffered)) != -1) {
				byteArrayOutputStream.write(buffered, 0, byteRead);
			}
			byteArrayOutputStream.flush();
			bufferedInputStream.close();
			byteArrayOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		}
	}
}
