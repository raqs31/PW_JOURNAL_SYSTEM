package pw.mario.faces.api;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pw.mario.journal.model.User;

/**
 * Interfejs dla elementu ui z listą użytkowników
 * @author MarioBross
 *
 */
public interface IUserList extends Serializable  {
	List<User> getUsers();
	
	void setUsers(List<User> users);
	
	boolean getReadOnly();
	
	void setReadOnly(boolean isReadOnly);
	
	User getSelectedUser();

	void setSelectedUser(User u);
	
	void onRowSelect(SelectEvent e);
	
	void onRowUnselect(UnselectEvent e);
} 
