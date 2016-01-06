package pw.mario.journal.service;

import java.io.Serializable;

public interface LoginService extends Serializable {

	String getLogin();

	boolean isLogged();

}