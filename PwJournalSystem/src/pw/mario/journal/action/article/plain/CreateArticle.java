package pw.mario.journal.action.article.plain;


import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class CreateArticle implements ButtonAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean allowed() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AUTHOR");
	}

	@Override
	public void doAction() throws PerformActionException {
	}

	@Override
	public String getAction() {
		return "newArticle?faces-redirect=true";
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
	}

	@Override
	public void setToRefresh(Refreshable toRefresh) {
	}
}
