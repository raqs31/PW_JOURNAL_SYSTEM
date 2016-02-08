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
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.LockException;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.Messages;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleLazyLoadingService;
import pw.mario.journal.service.article.ArticleOperationService;

@Button
@Action(actionFor=ArticleManager.AUTHOR)
@Named
@Dependent
public class UploadNewVersionAction implements ButtonAction {
	private static final long serialVersionUID = -4869406550164908694L;
	private Refreshable toRefresh;
	
	@Inject private ArticleOperationService operation;
	@Inject private LoginService ctx;
	@Inject private ArticleLazyLoadingService articleLazy;
	
	private Article article;
	
	@Override
	public boolean allowed() {
		if (article == null)
			return false;
		
		if (article.getStatus().addVersionEnabled()) {
			articleLazy.loadAuthors(article);
			if (article.getAuthors().contains(ctx.getCurrentUser()))
				return true;
		}
		
		if (article.getStatus().acceptorAddVersionEnabled()) {
			articleLazy.loadAcceptors(article);
			if (article.getAcceptors().contains(ctx.getCurrentUser()))
				return true;
		}
		return false;
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
			if (toRefresh != null)
				toRefresh.refresh();
			Messages.addMessage("Dodano nową wersję");
		} catch (LockException ex) { 
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
		} catch (PerformActionException e1) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage());
		}
	}

	@Override
	public void setArticle(Article a) {
		this.article=a;
	}

	@Override
	public void setToRefresh(Refreshable toRefresh) {
		this.toRefresh = toRefresh;
	}
}
