package pw.mario.journal.action.article.plain;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.JSFUtil;
import pw.mario.common.util.Messages;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.article.ArticleOperationService;

@Button
@Action(actionFor=ArticleManager.AUTHOR)
@Named
@Dependent
public class DeleteArticle implements ButtonAction {
	private static final long serialVersionUID = -4869406550164908694L;
	
	@Inject private ArticleOperationService operation;
	private Article article;
	
	@Override
	public boolean allowed() {
		return article != null;
	}

	@Override
	public void doAction() throws PerformActionException {
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/resources/jsf/areYouSure", options, null);		
	}

	@Override
	public String getAction() {
		return null;
	}

	@Override
	public String getValue() {
		return "Usuń";
	}

	@Override
	public String getId() {
		return "bDeleteArticle";
	}

	@Override
	public void onReturnEvent(SelectEvent e) {
		try {
			Object o = e.getObject();
			if (o == null)
				throw new PerformActionException("Błąd parametru zwrotnego");
			if (Boolean.TRUE.equals(o)) {
				operation.deleteArticle(article);
				Messages.keepMessages();
				Messages.addMessage("Usunięto artykuł");
				JSFUtil.redirect("articles.xhtml");
			} else {
				Messages.addMessage("Anulowano usuwanie");
			}
		} catch (PerformActionException e1) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage());
		}
	}

	@Override
	public void setArticle(Article a) {
		this.article=a;
	}
}
