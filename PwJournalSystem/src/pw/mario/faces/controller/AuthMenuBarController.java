package pw.mario.faces.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lombok.Data;
import pw.mario.journal.service.LocaleService;
import pw.mario.journal.service.LoginContextService;

@Data
@ManagedBean(name="authBean")
@SessionScoped
public class AuthMenuBarController {
	@Inject
	LoginContextService ctx; 
	
	@Inject
	LocaleService localeService;
	
	private Locale currentLocale;
	private List<Locale> supportedLocales;
	
	public void logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Logger.getLogger(AuthMenuBarController.class).debug("Trying to logout user");
        session.invalidate();
    }
	
	@PostConstruct
	private void init() {
		currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		supportedLocales = localeService.getSupportedLocales();
	}
	
	public void changeLocale(ValueChangeEvent e) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)e.getNewValue());
	}
	
	public boolean renderLogout() {
		return ctx.isLogged();
	}
}
