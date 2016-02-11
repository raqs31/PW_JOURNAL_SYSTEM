package pw.mario.journal.action.article.plain;

import java.util.NoSuchElementException;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pw.mario.common.exception.LockException;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.Messages;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;

@Button
@Action(actionFor=ArticleManager.ARTICLE_ACCEPTOR)
@Named
@Dependent
public class UploadArticleOpinion extends UploadNewVersionAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getValue() {
		return "Dodaj recenzję";
	}

	@Override
	public String getId() {
		return "bNewOpinion";
	}
	
	@Override
	public boolean allowed() {
		if (article == null)
			return false;
		
		if (article.getStatus().acceptorAddVersionEnabled()) {
			articleLazy.loadAcceptors(article);
			try {
				article.getAcceptors()
					.stream()
					.filter(acc -> acc.getAcceptor().equals(ctx.getCurrentUser())
							&& acc.getVersion() == null)
					.findAny()
					.get();
				return true;
			} catch (NoSuchElementException e) {
			}
		}
		return false;
	}
	
	@Override
	public void onReturnEvent(SelectEvent e) {
		try {
			Object o = e.getObject();
			if (o == null)
				throw new PerformActionException("Nie udało się przesłać pliku");
			
			operation.addAcceptorVersion(article, ctx.getCurrentUser(), (FileHandler)o);
			if (toRefresh != null)
				toRefresh.refresh();
			Messages.addMessage("Dodano nową wersję");
		} catch (LockException ex) { 
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
		} catch (PerformActionException e1) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage());
		}
	}
}
