package pw.mario.journal.service.article.action.list;

import javax.enterprise.context.Dependent;

import pw.mario.common.action.conditional.ConditionValidator;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Action;
import pw.mario.journal.qualifiers.ArticleManager;
import pw.mario.journal.qualifiers.Button;

@Button
@Action(actionFor=ArticleManager.AUTHOR)
@Dependent
public class Stub1 implements ButtonAction<Article> {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean allowed(ConditionValidator<Article> validator) {
		return true;
	}

	@Override
	public void doAction() throws PerformActionException {
	}

	@Override
	public String getAction() {
		System.out.println("WTF!");
		return "newArticle";
	}

	@Override
	public String getValue() {
		return "Utwórz artykuł STUB";
	}

	@Override
	public String getId() {
		return "bCreaArticleSTUB";
	}
}