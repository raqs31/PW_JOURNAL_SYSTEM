package pw.mario.journal.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.User;

@Default
@Dependent
public class ArticleDAOImpl extends AbstractDAOImpl <Article>implements ArticleDAO {

	@Override
	public void addArticle(Article a, User u) {
		User us = em.find(User.class, 1L);
		ArticleVersion ver = new ArticleVersion();
		ver.setArticle(a);
		ver.setVersionNum(1L);
		ver.setLastVersion(Boolean.TRUE);
		ver.setStatus("WRK");
		ver.setArticle(a);
		a.setManagementId(us);
		List<ArticleVersion> versions = new LinkedList<>();
		versions.add(ver);
		a.setVersions(versions);

		em.persist(a);

	}

}
