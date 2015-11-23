package pw.mario.journal.service;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import lombok.Data;

@Data
@Stateful
public class LoginContextService {
	@Resource
	private SessionContext ctx;
	
	public String getLogin() {return ctx.getCallerPrincipal().getName();} 
}
