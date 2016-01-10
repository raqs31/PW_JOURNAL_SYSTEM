package pw.mario.journal.service.impl;


import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.google.common.base.Strings;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.User;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.UserService;

@NoArgsConstructor
@SessionScoped
public class LoginContextServiceImpl implements LoginService {
	private static final long serialVersionUID = 1L;
	@Resource private SessionContext ctx;
	@Inject private UserService userService;
	private User currentUser = null;
	
	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ILoginService#getLogin()
	 */
	@Override
	public String getLogin() {return ctx.getCallerPrincipal().getName();} 
	
	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ILoginService#isLogged()
	 */
	@Override
	public boolean isLogged() {
		return !Strings.isNullOrEmpty(ctx.getCallerPrincipal().getName()) &&  !"anonymous".equals(ctx.getCallerPrincipal().getName());
	}

	@Override
	public User getCurrentUser() {
		if (currentUser == null)
			currentUser = userService.getUserByLogin(getLogin());
		return currentUser;
	}
}
