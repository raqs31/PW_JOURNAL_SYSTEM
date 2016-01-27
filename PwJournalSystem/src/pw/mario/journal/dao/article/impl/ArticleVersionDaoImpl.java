package pw.mario.journal.dao.article.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import org.hibernate.Hibernate;

import lombok.extern.log4j.Log4j;
import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;

@Log4j
@Default
@Dependent
public class ArticleVersionDaoImpl extends AbstractDAOImpl<ArticleVersion> implements ArticleVersionDao {

	@Override
	public ArticleVersion getLastVersion(Article a) {
		if (a.getVersions() == null || a.getVersions().isEmpty())
			return null;
		if (!Hibernate.isInitialized(a.getVersions())) {
			Hibernate.initialize(a.getVersions());
		}
		return a.getVersions().stream().filter(v -> v.getLastVersion() == true).findFirst().get();
	}

	@Override
	public List<ArticleVersion> getVersions(Article a) {
		return createNamedTypedQuery(ArticleVersion.Queries.ARTICLE_VERSION).setParameter(1, a.getArticleId()).getResultList();
	}

	@Override
	public ArticleVersion createNewVersion(Article a) {
		ArticleVersion last = null;
		try {
			last = getLastVersion(a);
		} catch (NoSuchElementException e) {
			log.debug("Nie znaleziono ostatniej wersji dla artykułu");
		}
		ArticleVersion next = new ArticleVersion();
		if (last != null) {
			last.setLastVersion(false);
			next.setVersionNum(last.getVersionNum()+1);
		} else {
			next.setVersionNum(1L);
		}
		next.setLastVersion(true);
		next.setArticle(a);
		
		return next;
	}

	@Override
	public String createArticleName(ArticleVersion version) {
		StringBuilder sb = new StringBuilder('@');
		sb.append(version.getVersionNum())
			.append('-')
			.append(version.getArticle().getArticleId() == null ? "NEW" : version.getArticle().getArticleId())
			.append('-')
			.append(version.getArticle().getName());
		return sb.toString();
	}

	@Override
	public ArticleVersion save(ArticleVersion v) {
		em.persist(v);
		return v;
	}

	@Override
	public void refresh(ArticleVersion v) {
		em.refresh(v);
	}

}
