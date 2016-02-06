package pw.mario.journal.action.co;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.common.api.ContextConstants;
import pw.mario.common.util.Messages;
import pw.mario.journal.data.ExecutionContext;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.Rule_;
import pw.mario.journal.model.User;
import pw.mario.journal.service.article.ArticleOperationService;

@Log4j
@Named
@ViewScoped
public class RuleActions implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter @Setter private List<User> availableManagements;
	@Getter @Setter private List<User> availableAcceptors;
	@Getter @Setter private ExecutionContext ctx;
	@Getter private Rule rule;
	@Getter private Long articleId;
	@Inject private ArticleOperationService articleService;
	
	@PostConstruct
	private void init() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long ruleId = null;

		try {
			ruleId = Long.parseLong(params.get(ContextConstants.PARAM_RULE_ID));
			articleId =Long.parseLong(params.get(ContextConstants.PARAM_ARTICLE_ID));
			
			log.debug("ArticleId: " + articleId);
			log.debug("RuleId: " + ruleId);
			
			rule = articleService.getRule(ruleId);
			availableAcceptors = articleService.getAvailableAcceptors(articleId);
			availableManagements = articleService.getAvailableManagements(articleId);
			ctx = new ExecutionContext();
		} catch (NumberFormatException e) {
			Messages.addMessage(FacesMessage.SEVERITY_FATAL, e.getMessage());
		} 
	}
	
	public void proceed() {
		log.debug("Proceed with: " + ctx);
		RequestContext.getCurrentInstance().closeDialog(ctx);
	}
	
	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}
	
	public boolean isPickManagementRendered() {
		return rule.getPickManager();
	}
	
	public boolean isPickAcceptorsRendered() {
		return rule.getPickAcceptors();
	}
}
