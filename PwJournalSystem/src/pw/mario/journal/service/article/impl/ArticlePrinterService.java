package pw.mario.journal.service.article.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.PRINTER)
@RolesAllowed("PRINTER")
public class ArticlePrinterService implements ArticleService {
	private static final long serialVersionUID = 1L;
	private static final String[] rolesAllowed = {"PRINTER"};
	
	@Override
	public List<Article> getArticles(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PermitAll
	public String[] rolesAllowed() {
		return rolesAllowed;
	}

	@Override
	public Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getArticle(Long id, User u) {
		// TODO Auto-generated method stub
		return null;
	}

}