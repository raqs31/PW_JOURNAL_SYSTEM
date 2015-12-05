package pw.mario.faces.api;

import java.util.List;

import pw.mario.journal.model.User;

/**
 * Interfejs dla elementu ui z listą użytkowników
 * @author MarioBross
 *
 */
public interface IUserList {
	List<User> getUsers();
	
	void setUsers(List<User> users);
	
	boolean getReadOnly();
	
	void setReadOnly(boolean isReadOnly);
	
	
} 
