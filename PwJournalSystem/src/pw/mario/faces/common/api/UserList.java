package pw.mario.faces.common.api;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pw.mario.journal.model.Department;
import pw.mario.journal.model.User;

/**
 * Interfejs dla elementu ui z listą użytkowników
 * @author MarioBross
 *
 */
public interface UserList extends Serializable  {
	List<User> getUsers();
	
	void setUsers(List<User> users);
	
	boolean getReadOnly();
	
	void setReadOnly(boolean isReadOnly);
	
	User getSelectedUser();

	void setSelectedUser(User u);
	
	void onRowSelect(SelectEvent e);
	
	void onRowUnselect(UnselectEvent e);
	
	void setDepartments(List<Department> dept);
	
	List<Department> getDepartments();
	
	void onUserEdit(RowEditEvent e);
} 
