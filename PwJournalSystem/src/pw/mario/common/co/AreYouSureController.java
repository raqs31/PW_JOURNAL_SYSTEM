package pw.mario.common.co;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import lombok.Data;
import pw.mario.common.action.Action;
import pw.mario.common.exception.PerformActionException;

@Data
@Named
@RequestScoped
public class AreYouSureController  {
	private List<ConfirmButton> actions;
	@PostConstruct
	private void init() {
		actions = new LinkedList<>();
		actions.add(new ConfirmButton(true));
		actions.add(new ConfirmButton(false));
	}
	public void actionListener(ActionEvent e) {
		RequestContext.getCurrentInstance().closeDialog(e.getSource());
	}
	@Data
	public class ConfirmButton implements Action {
		private Boolean toReturn;
		private String descr;
		private String icon;
		
		protected ConfirmButton(Boolean toRet) {
			this.toReturn = toRet;
			descr = toRet ?	"Tak" : "Nie";
			icon = toRet ? "ui-icon-check" : "ui-icon-close"; 
		}
		
		@Override
		public void doAction() throws PerformActionException {
			RequestContext.getCurrentInstance().closeDialog(toReturn);
		}
	}
}
