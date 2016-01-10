package pw.mario.journal.service.article.impl;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pw.mario.journal.dao.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.service.article.ArticleService;

@Stateless
@ArticleManagement(value=ArticleManager.AUTHOR)
@RolesAllowed("AUTHOR")
public class AuthorArticleService implements ArticleService {

	private static final long serialVersionUID = 1L;
	@Inject private ArticleDAO articleDao;
	private final String[] rolesAllowed = {"AUTHOR"};
	
	@Override
	public Article addArticle(Article a) {
		return null;
	}

	@Override
	public Article updateArticle(Article a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeArticle(Article a) {
		// TODO Auto-generated method stub
	}

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

}
