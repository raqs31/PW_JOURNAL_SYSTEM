package pw.mario.journal.service.article.impl;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.AUTHOR)
@RolesAllowed("AUTHOR")
public class AuthorArticleService implements ArticleService {
	private static final long serialVersionUID = 1L;
	private final String[] rolesAllowed = {"AUTHOR"};

	@Inject private ArticleDAO articleDao;
	@Inject @Button AbstractActionFactory<ButtonAction, Article> actionFactory;
	
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
	public Iterable<ButtonAction> getActions(Article a, User u) {
		return actionFactory.getActions(a, u);
	}

	@Override
	public Article getArticle(Long id, User u) {
		Article article = articleDao.getArticle(id);
		articleDao.loadDetails(article);
		return article;
	}

}
