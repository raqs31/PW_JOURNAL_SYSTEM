package pw.mario.journal.service.article;

import java.util.Collection;
import java.util.List;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.exception.RouteActionException;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.common.User;

public interface ArticleOperationService {
	void addNewVersion(Article a, FileHandler handler) throws PerformActionException;
	
	void addAcceptorVersion(Article a, User acceptor, FileHandler handler) throws PerformActionException;
	
	void deleteArticle(Article a) throws PerformActionException;
	
	Article getArticle(Long id, User u);
	
	List<Rule> getAvailableSteps(Article a, User u);
	
	Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh);
	
	void execute(ExecutionContext ctx) throws RouteActionException;
	
	List<User> getAvailableManagements(Long articleId);
	
	List<User> getAvailableAcceptors(Long articleId);

	Rule getRule(Long ruleId);
	
	void saveAcceptorsForms(Article a) throws PerformActionException;
	
}
