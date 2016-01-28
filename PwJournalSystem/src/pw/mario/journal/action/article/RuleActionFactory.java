package pw.mario.journal.action.article;

import java.util.Collection;
import java.util.LinkedList;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import lombok.extern.log4j.Log4j;
import pw.mario.common.action.AbstractActionFactory;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.exception.RouteActionException;
import pw.mario.common.util.Messages;
import pw.mario.journal.dao.article.ArticleDAO;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;
import pw.mario.journal.qualifiers.Rules;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.service.article.ArticleOperationService.ExecutionContext;

@Rules
@Dependent
public class RuleActionFactory extends AbstractActionFactory<ButtonAction, Article>{
	@Inject ArticleOperationService service;
	
	@Override
	public Collection<ButtonAction> getActions(Article a, User u ) {
		Collection<ButtonAction> availRules = new LinkedList<>();
		service.getAvailableSteps(a, u).forEach(r -> availRules.add(new RuleButtonAction(a, r, service)));

		return availRules;
	}

	@Log4j
	private static class RuleButtonAction implements ButtonAction {
		private static final long serialVersionUID = 1L;
		private Article article;
		private Rule rule;
		private ArticleOperationService articleService;
		
		protected RuleButtonAction(Article a, Rule r, ArticleOperationService service) {
			this.article = a;
			this.rule = r;
			this.articleService = service;
		}
		
		@Override
		public boolean allowed() {
			return true;
		}

		@Override
		public void doAction() throws PerformActionException {
			ExecutionContext ctx = ExecutionContext.builder().article(article).rule(rule).build();
			try {
				articleService.execute(ctx);
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

	}
}
