package pw.mario.journal.dao.article.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import org.hibernate.Hibernate;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;

@Default
@Dependent
public class ArticleVersionDaoImpl extends AbstractDAOImpl<ArticleVersion> implements ArticleVersionDao {

	@Override
	public ArticleVersion getLastVersion(Article a) {
		if (Hibernate.isInitialized(a.getVersions())) {
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
		ArticleVersion last = getLastVersion(a);
		ArticleVersion next = new ArticleVersion();
		if (last != null) {
			last.setLastVersion(false);
			next.setVersionNum(last.getVersionNum()+1);
		} else {
			next.setVersionNum(1L);
		}
		next.setArticle(a);
		
		return next;
	}

}