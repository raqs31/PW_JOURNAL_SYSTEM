package pw.mario.journal.dao.article;

import java.util.List;

import javax.persistence.LockModeType;

import pw.mario.common.exception.LockException;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.ArticleAcceptor;
import pw.mario.journal.model.article.ArticleHistory;
import pw.mario.journal.model.article.ArticleVersion;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.service.article.ExecutionContext;

public interface ArticleDAO {
	Article addArticle(Article a);

	List<Article> getUserArticles(User u);
	
	List<Article> getArticlesToAccept(User u);
	
	List<Article> getArticlesToManagement(User u);
	
	List<Article> getPrintableArticles(User u);
	
	List<User> getArticleAuthors(Article a);
	
	List<ArticleAcceptor> getArticleAcceptors(Article a);
	
	List<Tag> getArticleTags(Article a);
	
	Article getArticle(Long id);
	
	Article getRefreshedArticle(Long id);
	
	void loadDetails(Article a);
	
	List<Rule> getAvailableRules(Article a, User u);
	
	Article save(Article a);
	
	void deleteArticle(Article a);
	
	Article getLockedArticle(Article a, LockModeType lockType) throws LockException;
	
	Article getLockedArticle(Long id, LockModeType lockType);
	
	void refresh(Article a);

	ArticleHistory addArticleHistory(Article a, ArticleVersion v);

	ArticleHistory addArticleHistory(Article a, ExecutionContext ctx);
}
