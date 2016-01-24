package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.faces.articles.co.ArticleDetailsController;
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
	
	private List<Article> articles;
	
	@PostConstruct
	@PermitAll
	private void init() {
		actions = new LinkedList<>();
	}
	
	@Override
	public List<Article> getArticles() {
		if (articles == null)
			articles =articleService.getArticles(ctx.getCurrentUser());
		return articles;
	}

	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public String onEdit(Article a) {
		FacesContext.getCurrentInstance()
						.getExternalContext()
						.getFlash()
						.put(ArticleDetailsController.PARAM_ARTICLE_ID, a);
		return "articleDetails?faces-redirect=true";
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

	@Override
	public void onRowSelect(SelectEvent e) {
		FacesMessage msg = new FacesMessage("Selected " + e.getObject(), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
