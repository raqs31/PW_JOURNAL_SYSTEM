package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleService;

@Named
@ArticleTab
@Dependent
public class AuthorArticlesTab implements Serializable, ArticlesTab {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły autora";
	
	@Inject @ArticleManagement(ArticleManager.AUTHOR) private ArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "authors";
	@Getter private List<ButtonAction<Article>> actions;
	
	@PostConstruct
	private void init() {
		refreshAction();
	}
	
	@Override
	public List<Article> getArticles() {
		return articleService.getArticles(ctx.getCurrentUser());
	}

	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public String onEdit() {
		// TODO Auto-generated method stub
		return "articleDetail";
	}
	
	private void refreshAction() {
		if (actions == null)
			actions = new LinkedList<>();
		else
			actions.clear();
		
		for (ButtonAction<Article> b: articleService.getActions(selectedArticle, ctx.getCurrentUser()))
			actions.add(b);
	}
}
