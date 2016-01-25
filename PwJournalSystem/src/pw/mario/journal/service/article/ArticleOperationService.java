package pw.mario.journal.service.article;

import org.primefaces.model.UploadedFile;

import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.util.files.FileHandler;

public interface ArticleOperationService extends ArticleService {
	void addNewVersion(Article a, UploadedFile newFile) throws PerformActionException;
	
	void addNewVersion(Article a, FileHandler handler) throws PerformActionException;
}
