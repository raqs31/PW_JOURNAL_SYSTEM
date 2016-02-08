package pw.mario.journal.service.article.impl;

import java.util.HashSet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Hibernate;

import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.service.article.ArticleLazyLoadingService;

@Default
@Stateless
@Transactional(value=TxType.REQUIRED)
public class ArticleLazyLoadingServiceImpl implements ArticleLazyLoadingService {
	@Inject private ArticleDAO articleDao;
	
	@Override
	public void loadAuthors(Article a) {
		if (!Hibernate.isInitialized(a.getAuthors()))
			a.setAuthors(new HashSet<>(articleDao.getArticleAuthors(a)));
	}

	@Override
	public void loadTags(Article a) {
		if (!Hibernate.isInitialized(a.getTagList()))
			a.setTagList(new HashSet<>(articleDao.getArticleTags(a)));
	}

	@Override
	public void loadAcceptors(Article a) {
		if (!Hibernate.isInitialized(a.getAcceptors()))
			a.setAcceptors(articleDao.getArticleAcceptors(a));
	}

}
