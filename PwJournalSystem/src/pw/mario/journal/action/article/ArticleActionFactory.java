package pw.mario.journal.action.article;

import java.util.Collection;
import java.util.LinkedList;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class ArticleActionFactory extends AbstractActionFactory<ButtonAction, Article> {
	@Inject @Button private Instance<ButtonAction> actions;
	
	@Override
	public Collection<ButtonAction> getActions(Article a, User u ) {
		Collection<ButtonAction> toReturn = new LinkedList<>();
		
		actions.forEach(b -> {  
			b.setArticle(a);
			if (b.allowed())
				toReturn.add(b); 
			});
		
		return toReturn;
	}
}
