package pw.mario.faces.api.impl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.faces.api.UserList;
import pw.mario.journal.model.User;

@NoArgsConstructor
public class EditableUserList implements UserList {
	private static final long serialVersionUID = -6930829924611447508L;
	@Getter @Setter private List<User> users;
	@Setter private boolean readOnly;
	@Getter @Setter private User selectedUser;
	
	@Override
	public boolean getReadOnly() {
		return readOnly;
	}

	@Override
	public void onRowSelect(SelectEvent e) {
		FacesMessage msg = new FacesMessage("Wybrano użytkownika", ((User)e.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Override
	public void onRowUnselect(UnselectEvent e) {
		FacesMessage msg = new FacesMessage("Odznaczono użytkownika", e.getObject().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
