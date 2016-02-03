package pw.mario.journal.action.article.plain;

import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.faces.articles.co.ArticleDetailsController;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class Refresh implements ButtonAction {
	private static final long serialVersionUID = 1L;
	private Article article;

	@Override
	public boolean allowed() {
		return article != null;
	}

	@Override
	public void doAction() throws PerformActionException {
	}

	@Override
	public String getAction() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(ArticleDetailsController.PARAM_ARTICLE_ID,
				article);
		return "articleDetails?faces-redirect=true";
	}

	@Override
	public String getValue() {
		return "Odśwież";
	}

	@Override
	public String getId() {
		return "bRefreshArticle";
	}

	@Override
	public void onReturnEvent(SelectEvent e) {
	}

	@Override
	public String ajax() {
		return "false";
	}

	@Override
	public void setArticle(Article a) {
		this.article = a;
	}

	@Override
	public boolean refreshNeeded() {
		return false;
	}

}
