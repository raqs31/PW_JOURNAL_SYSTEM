package pw.mario.journal.action.article.plain;


import javax.enterprise.context.Dependent;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class ReturnToList implements ButtonAction {
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
		return "articles";
	}

	@Override
	public String getValue() {
		return "Powrót do listy";
	}

	@Override
	public String getId() {
		return "bReturnToList";
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
	public void setToRefresh(Refreshable toRefresh) {}
	
	@Override
	public boolean availableOnList() {
		return false;
	}
}
