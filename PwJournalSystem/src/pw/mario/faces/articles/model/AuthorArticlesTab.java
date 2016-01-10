package pw.mario.faces.articles.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.faces.common.action.UserAction;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleService;

@Named
@ArticleTab
@Dependent
@Priority(0)
public class AuthorArticlesTab implements Serializable, ArticlesTab {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły autora";
	
	@Inject @ArticleManagement(ArticleManager.AUTHOR) private ArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "authors";

	@Override
	public List<Article> getArticles() {
		return articleService.getArticles(ctx.getCurrentUser());
	}

	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public List<UserAction> getActions() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

}
