package pw.mario.common.action.form;

import java.io.Serializable;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.ConditionalAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.Article;

public interface ButtonAction extends ConditionalAction, Serializable {
	String getAction();
	String getValue();
	String getId();
	void onReturnEvent(SelectEvent e);
	
	default String ajax() {
		return "true";
	}
	
	void setArticle(Article a);
	
	default boolean refreshNeeded() {
		return false;
	}
	
	default void refresh(Refreshable toRefresh) {
		if (refreshNeeded())
			toRefresh.refresh();
	}
}
