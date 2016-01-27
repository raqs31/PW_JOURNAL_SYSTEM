package pw.mario.common.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.Cleanup;

public class FileUtils {
	
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[1024];
		int byteRead;
			
		while ((byteRead = in.read(buff)) > 0)
			out.write(buff, 0, byteRead);
	}
	
	public static FileHandler createTempFile(InputStream in, String fileName) throws IOException {
		File tmpFile = File.createTempFile(fileName, "tmp");
		@Cleanup OutputStream output = new FileOutputStream(tmpFile);
		FileHandler handler = null;
		
		copy(in, output);
		
		handler = new FileHandler(tmpFile, fileName);

		return handler;
	}

}
