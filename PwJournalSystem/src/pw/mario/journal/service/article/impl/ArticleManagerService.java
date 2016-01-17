package pw.mario.journal.service.article.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.ARTICLE_MANAGER)
@RolesAllowed("ARTICLE_MANAGER")
public class ArticleManagerService implements ArticleService, Serializable {
	private static final long serialVersionUID = 1L;
	@Inject private ArticleDAO articleDao;

	@Override
	@PermitAll
	public List<Article> getArticles(User u) {
		return articleDao.getArticlesToManagement(u);
	}

	@Override
	@PermitAll
	public String[] rolesAllowed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ButtonAction<Article>> getActions(Article a, User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
