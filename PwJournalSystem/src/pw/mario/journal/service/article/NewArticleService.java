package pw.mario.journal.service.article;

import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.Dictionary;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;

public interface NewArticleService {
	List<User> getUsersFromDepartment(Department d);
	
	void createArticle(Article a);
	
	List<Dictionary> getArticlesStatuses();
	
	List<Tag> getTags();
	
	Article initArticle();
	
}
