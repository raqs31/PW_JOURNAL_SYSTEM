package pw.mario.common.api.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import lombok.Data;
import pw.mario.common.api.PickListRoles;
import pw.mario.journal.model.common.SystemRole;

@Data
public class EditableUserPickListRoles implements PickListRoles {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(EditableUserPickListRoles.class);
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
