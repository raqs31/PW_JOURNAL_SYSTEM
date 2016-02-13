package pw.mario.journal.action.article;

import java.util.Collection;
import java.util.LinkedList;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.common.User;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class ArticleActionFactory extends AbstractActionFactory<ButtonAction, Article> {
	@Inject @Button private Instance<ButtonAction> actions;
	
	@Override
	public Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh ) {
		Collection<ButtonAction> toReturn = new LinkedList<>();
		
		actions.forEach(b -> {  
			b.setArticle(a);
			b.setToRefresh(toRefresh);
			if (b.allowed())
				toReturn.add(b); 
			});
		
		return toReturn;
	}
}
