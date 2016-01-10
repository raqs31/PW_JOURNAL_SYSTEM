package pw.mario.journal.service;

import java.io.Serializable;

import pw.mario.journal.model.User;

public interface LoginService extends Serializable {

	String getLogin();

	boolean isLogged();
	
	User getCurrentUser();

}