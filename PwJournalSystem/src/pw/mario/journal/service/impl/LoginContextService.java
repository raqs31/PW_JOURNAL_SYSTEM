package pw.mario.journal.service.impl;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import lombok.Data;
import pw.mario.journal.service.ILoginService;

@Data
@Stateful
public class LoginContextService implements ILoginService {
	@Resource
	private SessionContext ctx;
	
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
			return ctx.isCallerInRole("USER");
	
	}
}
