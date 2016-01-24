package pw.mario.journal.service.article.impl;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.AUTHOR)
@RolesAllowed("AUTHOR")
public class AuthorArticleService implements ArticleService {
	private static final long serialVersionUID = 1L;
	private final String[] rolesAllowed = {"AUTHOR"};

	@Inject private ArticleDAO articleDao;
	@Inject @Action(actionFor=ArticleManager.AUTHOR)
	private Instance<ButtonAction<Article>> actions;
	
	@Override
	@PermitAll
	public List<Article> getArticles(User u) {
		return articleDao.getUserArticles(u);
	}

	@Override
	@PermitAll
	public String[] rolesAllowed() {
		return rolesAllowed;
	}

	@Override
	public Iterable<ButtonAction<Article>> getActions(Article a, User u) {
		return actions;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Article getArticle(Long id, User u) {
		Article article = articleDao.getArticle(id);
		articleDao.loadDetails(article);
		return article;
	}

}
