package pw.mario.journal.service;

import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.util.files.FileHandler;

public interface FileManagerService {
	String saveFile(FileHandler file) throws PerformActionException;
}
