package pw.mario.faces.api.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import lombok.Data;
import pw.mario.faces.api.PickListRoles;
import pw.mario.journal.model.SystemRole;

@Data
public class EditableUserPickListRoles implements PickListRoles {
	private Logger log = Logger.getLogger(EditableUserList.class);
	private boolean editable;
	private DualListModel<SystemRole> pickListSystemRoles;
	
	public EditableUserPickListRoles(final List<SystemRole> source, final List<SystemRole> destination) {
		if (source == null)
			throw new NullPointerException("Source is null");
		else if (source.isEmpty())
			log.warn("Source list is empty");
		if (destination == null)
			throw new NullPointerException("Destination is null");
		
		pickListSystemRoles = new DualListModel<>();
		pickListSystemRoles.setSource(source);
		pickListSystemRoles.setTarget(destination);
	}
}