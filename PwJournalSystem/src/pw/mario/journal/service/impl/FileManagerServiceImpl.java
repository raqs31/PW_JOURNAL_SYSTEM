package pw.mario.journal.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.dao.dictionary.SystemParameterDao;
import pw.mario.journal.model.dictionaries.SystemParameter;
import pw.mario.journal.service.FileManagerService;
import pw.mario.journal.util.files.FileHandler;
import pw.mario.journal.util.files.FileUtils;

@Log4j
@Stateless
public class FileManagerServiceImpl implements FileManagerService {
	@Inject private SystemParameterDao systemParameter;
	
	@Override
	public String saveFile(FileHandler file) throws PerformActionException {
		String filePath = systemParameter.getParameter(SystemParameter.Parameters.ARTICLE_DIR).getPropertyValue();
		File toSave = new File(filePath + file.getFullName()); 
		log.debug("Save file: " + toSave.getAbsolutePath());
		try {
			if (!toSave.exists())
				toSave.createNewFile();
			@Cleanup InputStream input = new FileInputStream(file.getFile());
			@Cleanup OutputStream output = new FileOutputStream(toSave);
			FileUtils.copy(input, output);
			return toSave.getAbsolutePath();
		} catch (IOException e) {
			log.error("Error in saveFile", e);
			throw new PerformActionException(e.getMessage());
		} finally {
			file.getFile().delete();
		}
	}
}
