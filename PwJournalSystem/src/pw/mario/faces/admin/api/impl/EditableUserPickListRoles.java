package pw.mario.faces.admin.api.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.common.api.PickListRoles;
import pw.mario.journal.model.SystemRole;

@Data
@Log4j
public class EditableUserPickListRoles implements PickListRoles {
	private static final long serialVersionUID = 1L;
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
