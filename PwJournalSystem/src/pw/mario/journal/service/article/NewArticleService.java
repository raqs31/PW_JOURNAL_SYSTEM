package pw.mario.journal.service.article;

import java.util.List;

import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.common.Department;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.model.dictionary.Dictionary;

public interface NewArticleService {
	List<User> getUsers(Department d);
	
	Article createArticle(Article a, FileHandler file) throws PerformActionException;
	
	List<Dictionary> getArticlesStatuses();
	
	List<Tag> getTags();
	
	Article initArticle();
	
}
