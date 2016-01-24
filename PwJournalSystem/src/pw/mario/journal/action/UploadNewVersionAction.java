package pw.mario.journal.action;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import pw.mario.common.action.conditional.ConditionValidator;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;

@Button
@Action(actionFor=ArticleManager.AUTHOR)
@Named
@RequestScoped
public class UploadNewVersionAction implements ButtonAction<Article> {
	private static final long serialVersionUID = -4869406550164908694L;

	@Override
	public boolean allowed(ConditionValidator<Article> validator) {
		return true;
	}

	@Override
	public void doAction() throws PerformActionException {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/resources/jsf/uploadNewVersion", options, null);		
	}

	@Override
	public String getAction() {
		return null;
	}

	@Override
	public String getValue() {
		return "Nowa wersja";
	}

	@Override
	public String getId() {
		return "bNewVersion";
	}

	@Override
	public void onReturnEvent(SelectEvent e) {
		System.out.println(e.getObject());
		
	}
}
