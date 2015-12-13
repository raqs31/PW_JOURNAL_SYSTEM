package pw.mario.faces.credential.co;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
@ManagedBean(name="loginCO")
@SessionScoped
public class LoginController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;

	public void loginAction() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
		try {
			request.login(userName, password);
			ctx.redirect(request.getContextPath());
		} catch (ServletException e1) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login i/lub hasło", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Błąd podczas logowania", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} 
	}

}
