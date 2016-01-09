package pw.mario.faces.common.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	public static void addMessage(FacesMessage.Severity severity, final String message, final String details) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, message,  details));
	}
	
	public static void addMessage(FacesMessage.Severity severity, final String message) {
		addMessage(severity, message, null);
	}
	
	public static void addMessage(final String message) {
		addMessage(FacesMessage.SEVERITY_INFO , message, null);
	}
	
	public static void addAccessDeniedMessage(final String details) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Brak dostępu",  details));
	}
	
	public static void addAccessDeniedMessage() {
		addAccessDeniedMessage(null);
	}
}
