package pw.mario.journal.action;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

import pw.mario.common.action.conditional.ConditionValidator;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.JSFUtil;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.ArticleTab;
import pw.mario.journal.qualifiers.Button;
import pw.mario.journal.qualifiers.enums.ArticleManager;

@Button
@Action(actionFor=ArticleManager.AUTHOR)
@ApplicationScoped
public class CreateArticle implements ButtonAction<Article> {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean allowed(ConditionValidator<Article> validator) {
		return true;
	}

	@Override
	public void doAction() throws PerformActionException {
			JSFUtil.redirect("newArticle.xhtml");
	}

	@Override
	public String getAction() {
		return "newArticle";
	}

	@Override
	public String getValue() {
		return "Utwórz artykuł";
	}

	@Override
	public String getId() {
		return "bCreaArticle";
	}

}
