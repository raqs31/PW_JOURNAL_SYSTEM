package pw.mario.journal.action.article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import lombok.extern.log4j.Log4j;
import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.ContextConstants;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.exception.RouteActionException;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.Rule;
import pw.mario.journal.model.common.User;
import pw.mario.journal.qualifiers.Rules;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.service.article.ExecutionContext;
import pw.mario.journal.service.common.LoginService;

@Rules
@Dependent
public class RuleActionFactory extends AbstractActionFactory<ButtonAction, Article>{
	@Inject private ArticleOperationService service;
	@Inject private LoginService ctx;
	
	@Override
	public Collection<ButtonAction> getActions(Article a, User u, Refreshable toRefresh ) {
		Collection<ButtonAction> availRules = new LinkedList<>();
		
		
		for (Rule r : service.getAvailableSteps(a, u)) {
			ButtonAction action;
			if (r.withUserAction())
				action = new RuleWithAction(a, r, ctx.getCurrentUser(), service, toRefresh);
			else
				action = new RuleButtonAction(a, r, ctx.getCurrentUser() ,service, toRefresh);
			availRules.add(action);
		}
		return availRules;
	}

	@Log4j
	private static class RuleButtonAction implements ButtonAction {
		protected static final long serialVersionUID = 1L;
		protected Article article;
		protected Rule rule;
		protected User user;
		protected ArticleOperationService articleService;
		protected Refreshable toRefresh;
		
		
		protected RuleButtonAction(Article a, Rule r, User u, ArticleOperationService service, Refreshable toRefresh) {
			this.article = a;
			this.rule = r;
			this.articleService = service;
			this.toRefresh = toRefresh;
			this.user = u;
		}
		
		@Override
		public boolean allowed() {
			return true;
		}

		@Override
		public void doAction() throws PerformActionException {
			ExecutionContext ctx = ExecutionContext
									.builder()
									.article(article)
									.rule(rule)
									.user(user)
									.isValid(true)
									.build();
			try {
				articleService.execute(ctx);
				if (toRefresh != null)
					toRefresh.refresh();
				Messages.addMessage("Wykonano akcję: " + rule.getName());
			} catch (RouteActionException e) {
				log.error("Error in execute rule", e);
				Messages.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getDetails());
			}
		}

		@Override
		public String getAction() {
			return null;
		}

		@Override
		public String getValue() {
			return rule.getName();
		}

		@Override
		public String getId() {
			return "rule#" + rule.getRuleId();
		}

		@Override
		public void onReturnEvent(SelectEvent e) {}

		@Override
		public void setArticle(Article a) {
			throw new RuntimeException("Operation not supported for this class");
		}

		@Override
		public void setToRefresh(Refreshable toRefresh) {
			this.toRefresh = toRefresh;
		}
	}
	
	@Log4j
	private static class RuleWithAction extends RuleButtonAction {

		protected RuleWithAction(Article a, Rule r, User u, ArticleOperationService service, Refreshable toRefresh) {
			super(a, r, u, service, toRefresh);
		}

		private static final long serialVersionUID = 1L;

		@Override
		public void doAction() throws PerformActionException {
			Map<String,Object> options = new HashMap<>();
			Map<String, List<String>> params = new HashMap<>();
			
	        options.put("resizable", false);
	        options.put("draggable", false);
	        options.put("modal", true);
	        
	        List<String> articleParam = new ArrayList<String>(1);
	        List<String> ruleParam = new ArrayList<String>(1);
	        
	        ruleParam.add(rule.getRuleId().toString());
	        articleParam.add(article.getArticleId().toString());
	        
	        params.put(ContextConstants.PARAM_RULE_ID, ruleParam);
	        params.put(ContextConstants.PARAM_ARTICLE_ID, articleParam);
	        
	        log.debug("Open ruleActions for: " + rule + "\nArticle: " + article.getArticleId());
	        RequestContext.getCurrentInstance().openDialog("/resources/jsf/ruleActions", options, params);		
		}
	
		@Override
		public void onReturnEvent(SelectEvent e) {
			Object o = e.getObject();
			
			if (o == null) {
				Messages.addMessage(null, "Przerwano proces:", rule.getName());
			} else {
				ExecutionContext tmp = (ExecutionContext) o;
				ExecutionContext ctx = ExecutionContext.builder()
						.article(article)
						.rule(rule)
						.manager(tmp.getManager())
						.acceptors(tmp.getAcceptors())
						.isValid(true)
						.build();
				try {
					articleService.execute(ctx);
					Messages.addMessage("Wykonano akcję: " + rule.getName());
					if (toRefresh != null)
						toRefresh.refresh();
				} catch (RouteActionException e1) {
					log.error("Error on execute", e1);
					Messages.addMessage(FacesMessage.SEVERITY_ERROR, "Nie udało się wykonać akcji", e1.getMessage());
				}
			}
		}
	}
}
