package pw.mario.journal.action.article.plain;


import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.JSFUtil;
import pw.mario.faces.articles.co.ArticleDetailsController;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class CreateArticle implements ButtonAction {
	private static final long serialVersionUID = 1L;
	private Article article;
	@Override
	public boolean allowed() {
		return article == null;
	}

	@Override
	public void doAction() throws PerformActionException {
			JSFUtil.redirect("newArticle.xhtml");
	}

	@Override
	public String getAction() {
		return "articleDetails?faces-redirect=true";
	}

	@Override
	public String getValue() {
		return "Utwórz artykuł";
	}

	@Override
	public String getId() {
		return "bCreaArticle";
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

}
