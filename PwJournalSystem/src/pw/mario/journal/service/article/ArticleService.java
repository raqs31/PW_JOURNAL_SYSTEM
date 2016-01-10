package pw.mario.journal.service.article;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;

public interface ArticleService extends Serializable {
	Article addArticle(Article a);
	Article updateArticle(Article a);
	void removeArticle(Article a);
	List<Article> getArticles(User u);
	
	String[] rolesAllowed();
	
}
