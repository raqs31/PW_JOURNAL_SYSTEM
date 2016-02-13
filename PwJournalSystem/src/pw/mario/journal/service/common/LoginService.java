package pw.mario.journal.service.common;

import java.io.Serializable;

import pw.mario.journal.model.common.User;

public interface LoginService extends Serializable {

	String getLogin();

	boolean isLogged();
	
	User getCurrentUser();
	
	void reloadUser();

}