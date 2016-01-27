package pw.mario.journal.dao.article.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;

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
		return null;
	}

	@Override
	public List<Article> getArticlesToManagement(User u) {
		return null;
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
		return find(id);
	}

	@Override
	public void loadDetails(Article a) {
		if (!em.contains(a))
			reAttachEntity(a);
		a.getAuthors().size();
		a.getTagList().size();
		a.getVersions().size();
	}

	@Override
	public List<Rule> getAvailableRules(Article a, User u) {
		return em.createNamedQuery(Rule.Queries.NEXT_STEPS, Rule.class).setParameter(1, u).setParameter(2, a).getResultList();
	}

	@Override
	public Article save(Article a) {
		a = merge(a);
		em.flush();
		em.refresh(a);
		return a;
	}
}
