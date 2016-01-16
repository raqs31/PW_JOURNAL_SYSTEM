package pw.mario.common.api;

import java.io.Serializable;

import org.primefaces.model.DualListModel;

import pw.mario.journal.model.SystemRole;

public interface PickListRoles extends Serializable {
	boolean isEditable();
	void setEditable(boolean isEditable);
	
	DualListModel<SystemRole> getPickListSystemRoles();
	void setPickListSystemRoles(DualListModel<SystemRole> roles);
}
