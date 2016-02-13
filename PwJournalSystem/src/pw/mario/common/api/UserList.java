package pw.mario.common.api;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pw.mario.journal.model.common.User;

/**
 * Interfejs dla elementu ui z listą użytkowników
 * 
 * @author MarioBross
 *
 */
public interface UserList extends Serializable {
	List<User> getUsers();

	void setUsers(List<User> users);

	List<User> getSelectedUsers();

	void setSelectedUsers(List<User> u);

	void onRowSelect(SelectEvent e);

	void onRowUnselect(UnselectEvent e);

	void onUserEdit(RowEditEvent e);

	boolean getLoginRendered();

	boolean getEmailRendered();
}
