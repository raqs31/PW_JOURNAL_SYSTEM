package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.qualifiers.enums.ArticleManager;
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
	@PermitAll
	private void init() {
		actions = new LinkedList<>();
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

	@Override
	public boolean tabAllowed() {
		if (articleService.rolesAllowed() != null)
			for (String s: articleService.rolesAllowed())
				if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole(s))
					return true;
		return false;
	}

	@Override
	public void refreshActions() {
		if (actions == null)
			actions = new LinkedList<>();
		else
			actions.clear();
		
		for (ButtonAction<Article> b: articleService.getActions(selectedArticle, ctx.getCurrentUser()))
			actions.add(b);
	}

}
