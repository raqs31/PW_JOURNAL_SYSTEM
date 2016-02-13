package pw.mario.journal.service.common;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.common.SystemRole;
import pw.mario.journal.model.common.User;

public interface SystemRolesService extends Serializable {

	List<SystemRole> getSystemRoles();

	List<SystemRole> getExclusiveSystemRoles(User u);

}