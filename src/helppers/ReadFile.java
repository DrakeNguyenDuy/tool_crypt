package helppers;

import java.io.BufferedReader;
import java.io.File;
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

	public String readFile(String path, JPanel jPanel) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
			String textLoad = "";
			String line;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					textLoad += line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			bufferedReader.close();
			return textLoad;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
