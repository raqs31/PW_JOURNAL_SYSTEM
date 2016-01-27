package pw.mario.journal.action.article;

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
public class UploadNewVersionAction implements ButtonAction {
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
		try {
			Object o = e.getObject();
			if (o == null)
				throw new PerformActionException("Nie udało się przesłać pliku");
			
			operation.addNewVersion(article, (FileHandler)o);
		} catch (PerformActionException e1) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage());
		}
	}

	@Override
	public void setArticle(Article a) {
		this.article=a;
	}
}
