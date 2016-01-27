package pw.mario.journal.service.article;

import java.util.List;

import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;

public interface NewArticleService {
	List<User> getUsers(Department d);
	
	Article createArticle(Article a, FileHandler file) throws PerformActionException;
	
	List<Dictionary> getArticlesStatuses();
	
	List<Tag> getTags();
	
	Article initArticle();
	
}
