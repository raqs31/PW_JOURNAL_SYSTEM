package pw.mario.journal.service.article;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;

public interface ArticleService extends Serializable {
	List<Article> getArticles(User u);
	
	String[] rolesAllowed();
	
	Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh);
	
	Article getArticle(Long id, User u);
}
