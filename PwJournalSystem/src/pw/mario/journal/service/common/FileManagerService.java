package pw.mario.journal.service.common;

import org.primefaces.model.UploadedFile;

import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.file.FileHandler;

public interface FileManagerService {
	String saveFile(FileHandler file) throws PerformActionException;
	
	String saveFile(UploadedFile file, String fileName) throws PerformActionException;
	
	FileHandler saveTmpFile(UploadedFile file) throws PerformActionException;
}
