package pw.mario.faces.articles.model.tabs;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
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
public class AcceptorArticleTab implements Serializable, ArticlesTab {
	private static final long serialVersionUID = 1L;
	private static final String TITTLE = "Artykuły do akceptacji";
	
	@Inject @ArticleManagement(ArticleManager.ARTICLE_ACCEPTOR) private ArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private Article selectedArticle;
	@Getter private final String id = "acceptors";
	@Getter private Collection<ButtonAction> actions;
	
	private List<Article> articles;
	
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
	public void refresh() {
		if (selectedArticle != null) {
			selectedArticle = articleService.getArticle(selectedArticle.getArticleId(), ctx.getCurrentUser());
			int articleIndex = 0;
			
			for (Article a: articles) {
				if (a.getArticleId().compareTo(selectedArticle.getArticleId()) == 0) {
					articles.remove(articleIndex);
					articles.add(articleIndex, selectedArticle);
					break;
				}
				articleIndex++;
			}
		}
		reloadActions();
	}

	@Override
	public void onRowSelect(SelectEvent e) {
		reloadActions();
	}
	
	private void reloadActions() {
		actions = articleService.getActions(selectedArticle, ctx.getCurrentUser(), this);
			
	}
}
