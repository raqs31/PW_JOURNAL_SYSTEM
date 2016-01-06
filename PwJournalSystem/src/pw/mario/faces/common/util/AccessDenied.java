package pw.mario.faces.common.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AccessDenied {
	public static void addAccessDeniedMessage(final String details) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Brak dostępu",  details));
	}
	
	public static void addAccessDeniedMessage() {
		addAccessDeniedMessage(null);
	}
}
