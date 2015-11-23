package pw.mario.faces;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lombok.Data;
import pw.mario.journal.service.LoginContextService;

@Data
@ManagedBean(name="authBean")
@SessionScoped
public class AuthorizationBean {
	@Inject
	LoginContextService ctx; 
	
	public void logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Logger.getLogger(AuthorizationBean.class).debug("Trying to logout user");
        session.invalidate();
    }
}
