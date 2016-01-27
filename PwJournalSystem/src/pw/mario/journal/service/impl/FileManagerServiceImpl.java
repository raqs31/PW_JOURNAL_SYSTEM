package pw.mario.journal.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.google.common.io.Files;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.common.util.file.FileUtils;
import pw.mario.journal.dao.dictionary.SystemParameterDao;
import pw.mario.journal.model.dictionaries.SystemParameter;
import pw.mario.journal.service.FileManagerService;

@Log4j
@Named
@ApplicationScoped
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

	@Override
	public String saveFile(UploadedFile file, String fileName) throws PerformActionException {
		StringBuilder filePath = new StringBuilder(systemParameter.getParameter(SystemParameter.Parameters.ARTICLE_DIR).getPropertyValue())
					.append(fileName)
					.append('.')
					.append(Files.getFileExtension(Files.getFileExtension(file.getFileName())));
		String path = filePath.toString();
		try {
			File newFile = new File(path);
			newFile.createNewFile();
			file.write(path);
		} catch (IOException io) {
			log.error("Error in create new file", io);
			throw new PerformActionException(io.getMessage());
		} catch (Exception e) {
			log.error("Error in writeFile",e);
			throw new PerformActionException(e.getMessage());
		}
		return path;
	}

	@Override
	public FileHandler saveTmpFile(UploadedFile file) throws PerformActionException {
		try {
			File tmp = File.createTempFile(file.getFileName(), "atmp");
			file.write(tmp.getAbsolutePath());
			return new FileHandler(tmp, file.getFileName());
		} catch (Exception e) {
			log.error("Error in writeFile",e);
			throw new PerformActionException(e.getMessage());
		}
	}
}
