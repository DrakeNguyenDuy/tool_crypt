package helppers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
	private static WriteFile writeFile=null;
	public static WriteFile getInstance() {
		if(writeFile==null) {
			writeFile = new WriteFile();
			return writeFile;
		}
		return writeFile;
	}

	public void writeFile(String path, byte[] byteWrite) throws IOException {
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
		bufferedOutputStream.write(byteWrite);
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
	}
}
