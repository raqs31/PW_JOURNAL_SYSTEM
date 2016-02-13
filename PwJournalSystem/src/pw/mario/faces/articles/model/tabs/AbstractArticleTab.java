package pw.mario.faces.articles.model.tabs;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.faces.articles.model.ArticlesTab;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.service.article.ArticleService;
import pw.mario.journal.service.common.LoginService;

public abstract class AbstractArticleTab implements ArticlesTab {
	@Getter @Setter private Article selectedArticle;
	@Getter private Collection<ButtonAction> actions;
	@Inject private LoginService ctx;
	private List<Article> articles;
	
	protected abstract ArticleService getArticleService();
	
	protected void reloadActions() {
		actions = getArticleService().getActions(selectedArticle, ctx.getCurrentUser(), this)
					.stream()
					.filter(b -> b.availableOnList())
					.collect(Collectors.toList());
	}
	
	@Override
	public void onRowSelect(SelectEvent e) {
		reloadActions();
	}
	
	@Override
	public void refresh() {
		if (selectedArticle != null) {
			selectedArticle = getArticleService().getArticle(selectedArticle.getArticleId(), ctx.getCurrentUser());
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
	public List<Article> getArticles() {
		if (articles == null)
			articles =getArticleService().getArticles(ctx.getCurrentUser());
		return articles;
	}
	
	@Override
	public boolean tabAllowed() {
		if (getArticleService().rolesAllowed() != null)
			for (String s: getArticleService().rolesAllowed())
				if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole(s))
					return true;
		return false;
	}
}
