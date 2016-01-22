package pw.mario.journal.service.article;

import java.util.List;

import org.primefaces.model.UploadedFile;

import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.util.files.FileHandler;

public interface NewArticleService {
	List<User> getUsers(Department d);
	
	void createArticle(Article a, FileHandler file) throws PerformActionException;
	
	void createArticle(Article a, UploadedFile file) throws PerformActionException;
	
	List<Dictionary> getArticlesStatuses();
	
	List<Tag> getTags();
	
	Article initArticle();
	
}
