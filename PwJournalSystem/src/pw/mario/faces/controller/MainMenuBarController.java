package pw.mario.faces.controller;

import java.io.IOException;
import java.io.Serializable;
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

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.service.ILocaleService;
import pw.mario.journal.service.ILoginService;

@ManagedBean(name="authBean")
@SessionScoped
public class MainMenuBarController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ILoginService ctx; 
	
	@Inject
	private ILocaleService localeService;
	
	@Getter @Setter private Locale currentLocale;
	@Getter private List<Locale> supportedLocales;
	
	public void logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Logger.getLogger(MainMenuBarController.class).debug("Trying to logout user");
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
	
	public boolean renderLogin() {
		return !ctx.isLogged() && !FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath().contains("login");
	}
	
	public String getLogin() {
		return ctx.getLogin();
	}
}
