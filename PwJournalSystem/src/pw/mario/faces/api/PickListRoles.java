package pw.mario.faces.api;

import org.primefaces.model.DualListModel;

import pw.mario.journal.model.SystemRole;

public interface PickListRoles {
	boolean isEditable();
	void setEditable(boolean isEditable);
	
	DualListModel<SystemRole> getPickListSystemRoles();
	void setPickListSystemRoles(DualListModel<SystemRole> roles);
}
