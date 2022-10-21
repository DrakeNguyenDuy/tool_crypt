package helppers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class WriteFile {
	private static WriteFile writeFile=null;
	public static WriteFile getInstance() {
		if(writeFile==null) {
			writeFile = new WriteFile();
			return writeFile;
		}
		return writeFile;
	}
	public void writeFile(String path, byte[] byteWrite) {
		try {
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bufferedOutputStream.flush();
			bufferedOutputStream.write(byteWrite);
			bufferedOutputStream.close();
			JOptionPane.showMessageDialog(null, "Save file success!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Opps file not found!!");
		} catch (IOException e) {

		}
	}
}
