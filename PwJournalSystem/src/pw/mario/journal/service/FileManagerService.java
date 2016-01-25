package pw.mario.journal.service;

import org.primefaces.model.UploadedFile;

import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.util.files.FileHandler;

public interface FileManagerService {
	String saveFile(FileHandler file) throws PerformActionException;
	
	String saveFile(UploadedFile file, String fileName) throws PerformActionException;
	
	FileHandler saveTmpFile(UploadedFile file) throws PerformActionException;
}
