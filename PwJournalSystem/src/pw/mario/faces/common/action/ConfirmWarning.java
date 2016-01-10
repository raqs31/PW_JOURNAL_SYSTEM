package pw.mario.faces.common.action;


import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.common.exception.PerformActionException;

@Log4j
@NoArgsConstructor
public class ConfirmWarning implements OnConfirmAction {
	@Setter private Action action;
	@Getter @Setter private String message;
	@Setter private String buttonValue;
	private boolean actionSucces = false;
	@Setter @Getter private String toRedirect;
	
	public ConfirmWarning(String toRedirect) {
		this.toRedirect = toRedirect;
	}
	
	@Override
	public void doAction() {
		if (action == null)
			throw new IllegalStateException("Action cannot be null");
		try {
			action.doAction();
			actionSucces = true;
		} catch (PerformActionException e) {
			actionSucces = false;
		}
	}
	
	@Override
	public String getHeader() {
		return "Potwierdzenie";
	}

	@Override
	public String getIcon() {
		return "ui-icon-alert";
	}

	@Override
	public String buttonValue() {
		return buttonValue;
	}

	@Override
	public String afterAction() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			if (actionSucces)
				ctx.getExternalContext().redirect("users.xhtml");
		} catch (IOException e) {
			log.error("Error while redirect", e);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd podczas przekierowania", null);
			ctx.addMessage(null, msg);
			e.printStackTrace();
		}
		return null;
	}
}
