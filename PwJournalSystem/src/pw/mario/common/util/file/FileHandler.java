package pw.mario.common.util.file;

import java.io.File;

import com.google.common.io.Files;

import lombok.Getter;
import lombok.Setter;

public class FileHandler {
	@Getter private final File file;
	@Getter @Setter private String fileName;
	@Getter private String extension;
	
	public FileHandler(File file, String fileName) {
		this.file = file;
		this.fileName = Files.getNameWithoutExtension(fileName);
		this.extension = Files.getFileExtension(fileName);
	}
	
	public String getFullName() {
		return fileName + "." + extension;
	}
}
