package pw.mario.journal.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.ArticleVersion;
import pw.mario.journal.model.User;

@Singleton
public class ArticleDAOImpl extends AbstractDAOImpl implements ArticleDAO {

	@Override
	public void addArticle(Article a, User u) {
		User us = em.find(User.class, 1L);
		ArticleVersion ver = new ArticleVersion();
		ver.setArticle(a);
		ver.setVersionNum(1L);
		ver.setLastVersion(Boolean.TRUE);
		ver.setStatus("WRK");
		ver.setArticle(a);
//		a.setManagementId(us);
		List<ArticleVersion> versions = new LinkedList<>();
		versions.add(ver);
		a.setVersions(versions);
		
		em.persist(a);
		

	}

}
