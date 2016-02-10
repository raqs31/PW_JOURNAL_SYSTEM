package pw.mario.journal.action.article.plain;

import java.util.NoSuchElementException;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

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

}
