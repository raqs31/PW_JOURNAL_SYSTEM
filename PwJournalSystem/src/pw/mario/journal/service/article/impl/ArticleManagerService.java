package pw.mario.journal.service.article.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.common.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.ARTICLE_MANAGER)
@RolesAllowed("ARTICLE_MANAGER")
public class ArticleManagerService implements ArticleService {
	private static final long serialVersionUID = 1L;
	
	@Inject private ArticleDAO articleDao;
	@Inject private ArticleOperationService articleOperation;
	
	private String[] rolesAllowed = {"ARTICLE_MANAGER"};
	
	@Override
	public List<Article> getArticles(User u) {
		return articleDao.getArticlesToManagement(u);
	}

	@Override
	@PermitAll
	public String[] rolesAllowed() {
		return rolesAllowed;
	}

	@Override
	public Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh) {
		return articleOperation.getActions(a, u, toRefresh);
	}

	@Override
	public Article getArticle(Long id, User u) {
		return articleDao.getRefreshedArticle(id);
	}

}
