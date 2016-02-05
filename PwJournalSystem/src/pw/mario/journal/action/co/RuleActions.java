package pw.mario.journal.action.co;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import lombok.extern.log4j.Log4j;

@Log4j
@Named
@RequestScoped
public class RuleActions {
	
	public void proceed() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}
}
