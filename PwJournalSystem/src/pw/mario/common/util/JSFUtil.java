package pw.mario.common.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.extern.log4j.Log4j;

@Log4j
public class JSFUtil {
	public static void redirect(String URL) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(URL);
		} catch (IOException e) {
			log.error("Error when redirect to: " + URL, e);
			Messages.keepMessages();
			Messages.addMessage(FacesMessage.SEVERITY_FATAL, "Błąd podczas przekierowania", e.getMessage());
		}
	}
}
