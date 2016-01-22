package pw.mario.journal.dao.article.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;

@Default
@Dependent
public class ArticleDAOImpl extends AbstractDAOImpl <Article>implements ArticleDAO {

	@Override
	public Article addArticle(Article a) {
		persist(a);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesToManagement(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
