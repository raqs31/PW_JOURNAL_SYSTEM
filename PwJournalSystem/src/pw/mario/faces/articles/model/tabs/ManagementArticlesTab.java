package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

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
public class ManagementArticlesTab implements Serializable, ArticlesTab {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły zarządzane";
	
	@Inject @ArticleManagement(ArticleManager.ARTICLE_MANAGER) private ArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private List<Article> articles;
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "articleMgmt";
	
	@PostConstruct
	private void init() {
		articles = articleService.getArticles(ctx.getCurrentUser());
	}

	@Override
	public Iterable<ButtonAction> getActions() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getTittle() {
		return TITTLE;
	}

	@Override
	public String onEdit(Article a) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRowSelect(SelectEvent e) {
		// TODO Auto-generated method stub
		
	}

}
