package pw.mario.faces.credential.co;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.service.common.LocaleService;
import pw.mario.journal.service.common.LoginService;

@Named("authBean")
@SessionScoped
@Stateful
@PermitAll
public class MainMenuBarController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginService ctx; 
	@Inject
	private LocaleService localeService;
	
	@Getter @Setter private Locale currentLocale;
	@Getter private List<Locale> supportedLocales;
	
	public String logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Logger.getLogger(MainMenuBarController.class).debug("Trying to logout user");
        session.invalidate();
        return "index";
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
