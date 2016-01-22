package pw.mario.journal.util.files;

import java.io.File;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class FileHandler {
	@Getter private final File file;
	@Getter @Setter(value=AccessLevel.PACKAGE) private String fileName;
	@Getter @Setter(value=AccessLevel.PACKAGE) private String extension;
	
	protected FileHandler(File file) {
		this.file = file;
	}
	
	protected void extractFromName(String fileName) {
		this.fileName = fileName.substring(0, fileName.lastIndexOf("."));
		this.extension = fileName.substring(fileName.lastIndexOf("."));
	}
	
	public String getFullName() {
		return fileName + "." + extension;
	}
}
