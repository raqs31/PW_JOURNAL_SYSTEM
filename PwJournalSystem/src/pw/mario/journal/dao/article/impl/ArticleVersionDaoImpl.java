package pw.mario.journal.dao.article.impl;

import org.hibernate.Hibernate;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.article.ArticleVersionDao;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;

public class ArticleVersionDaoImpl extends AbstractDAOImpl<ArticleVersion> implements ArticleVersionDao {

	@Override
	public ArticleVersion getLastVersion(Article a) {
		if (Hibernate.isInitialized(a.getVersions())) {
			Hibernate.initialize(a.getVersions());
		}
		return a.getVersions().stream().filter(v -> v.getLastVersion() == true).findFirst().get();
	}

	@Override
	public ArticleVersion getVersions(Article a) {
		return null;
	}

	@Override
	public ArticleVersion createNewVersion(Article a) {
		// TODO Auto-generated method stub
		return null;
	}

}
