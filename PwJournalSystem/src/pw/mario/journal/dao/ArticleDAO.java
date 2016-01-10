package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;

public interface ArticleDAO {
	Article addArticle(Article a);

	List<Article> getUserArticles(User u);
	
	List<Article> getArticlesToAccept(User u);
	
	List<Article> getArticlesToManagement(User u);
}
