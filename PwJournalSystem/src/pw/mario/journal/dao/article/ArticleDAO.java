package pw.mario.journal.dao.article;

import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;

public interface ArticleDAO {
	Article addArticle(Article a);

	List<Article> getUserArticles(User u);
	
	List<Article> getArticlesToAccept(User u);
	
	List<Article> getArticlesToManagement(User u);
	
	List<User> getArticleAuthors(Article a);
	
	List<Tag> getArticleTags(Article a);
	
	Article getArticle(Long id);
	
	void loadDetails(Article a);
	
	List<Rule> getAvailableRules(Article a, User u);
	
	Article save(Article a);
	
	void deleteArticle(Article a);
}
