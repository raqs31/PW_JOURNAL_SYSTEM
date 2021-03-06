package pw.mario.journal.dao.article.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.LockModeType;

import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.LockException;
import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.ArticleAcceptor;
import pw.mario.journal.model.article.ArticleHistory;
import pw.mario.journal.model.article.ArticleVersion;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.service.article.ExecutionContext;

@Log4j
@Default
@Dependent
public class ArticleDAOImpl extends AbstractDAOImpl <Article>implements ArticleDAO {

	@Override
	public Article addArticle(Article a) {
		persist(a);
		em.flush();
		em.refresh(a);
		return a;
	}

	@Override
	public List<Article> getUserArticles(User u) {
		return createNamedTypedQuery(Article.Queries.USER_ARTICLES)
				.setParameter(1, u.getId())
				.getResultList();
	}

	@Override
	public List<Article> getArticlesToAccept(User u) {
		return createNamedTypedQuery(Article.Queries.ACCEPTOR_ARTICLE).setParameter(1, u).getResultList();
	}

	@Override
	public List<Article> getArticlesToManagement(User u) {
		return createNamedTypedQuery(Article.Queries.MANAGER_ARTICLE).setParameter(1, u).getResultList();
	}

	@Override
	public List<User> getArticleAuthors(Article a) {
		return em.createNamedQuery(Article.Queries.ARTICLE_AUTHORS, User.class)
					.setParameter(1, a.getArticleId())
					.getResultList();
	}

	@Override
	public List<Tag> getArticleTags(Article a) {
		return em.createNamedQuery(Article.Queries.ARTICLE_TAGS, Tag.class)
				.setParameter(1, a.getArticleId())
				.getResultList();
	}

	@Override
	public Article getArticle(Long id) {
		Article a = find(id);
		return a;
	}

	@Override
	public void loadDetails(Article a) {
		if (!em.contains(a))
			reAttachEntity(a);
		a.getAuthors().size();
		a.getTagList().size();
		a.getVersions().size();
		a.getHistory().size();
		a.getAcceptors().size();
	}

	@Override
	public List<Rule> getAvailableRules(Article a, User u) {
		return em.createNamedQuery(Rule.Queries.NEXT_STEPS, Rule.class).setParameter(1, u).setParameter(2, a).getResultList();
	}

	@Override
	public Article save(Article a) {
		a = merge(a);
		em.flush();
		return a;
	}
	
	@Override
	public void deleteArticle(Article a) {
		delete(a);
	}

	@Override
	public Article getLockedArticle(Article a, LockModeType lockType) throws LockException {
		Article toRet = getLockedArticle(a.getArticleId(), lockType);
		
		if (toRet.getObjectVersionNumber().compareTo(a.getObjectVersionNumber()) != 0)
			throw new LockException("W międzyczasie zmienił się stan artykułu", a);
		
		return toRet;
	}

	@Override
	public Article getLockedArticle(Long id, LockModeType lockType) {
		Article toRet = getArticle(id);
		em.refresh(toRet);
		em.lock(toRet, lockType);
		return toRet;
	}

	@Override
	public void refresh(Article a) {
		em.refresh(a);
	}

	@Override
	public List<Article> getPrintableArticles(User u) {
		return createNamedTypedQuery(Article.Queries.PRINTABLE_ARTICLE).getResultList();
	}

	@Override
	public Article getRefreshedArticle(Long id) {
		Article toReturn = getArticle(id);
		refresh(toReturn);
		return toReturn;
	}

	@Override
	public List<ArticleAcceptor> getArticleAcceptors(Article a) {
		return em.createNamedQuery(Article.Queries.ARTICLE_ACCEPTORS, ArticleAcceptor.class).setParameter(1, a).getResultList();
	}

	@Override
	public ArticleHistory addArticleHistory(Article a, ArticleVersion v) {
		ArticleHistory history = new ArticleHistory();
		history.setArticle(a);
		history.setVersion(v);
		em.persist(history);
		return history;
	}

	@Override
	public ArticleHistory addArticleHistory(Article a, ExecutionContext ctx) {
		ArticleHistory history = new ArticleHistory();
		history.setArticle(a);
		history.setRule(ctx.getRule());
		em.persist(history);
		return history;
	}

}
