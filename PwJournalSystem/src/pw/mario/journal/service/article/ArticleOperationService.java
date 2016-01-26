package pw.mario.journal.service.article;

import java.util.Collection;
import java.util.List;

import org.primefaces.model.UploadedFile;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;
import pw.mario.journal.util.files.FileHandler;

public interface ArticleOperationService {
	void addNewVersion(Article a, UploadedFile newFile) throws PerformActionException;
	
	void addNewVersion(Article a, FileHandler handler) throws PerformActionException;
	
	Article getArticle(Long id, User u);
	
	List<Rule> getAvailableSteps(Article a, User u);
	
	Collection<ButtonAction> getActions(Article a, User u);
}
