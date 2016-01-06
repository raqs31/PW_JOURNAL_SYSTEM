package pw.mario.journal.service;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;

public interface SystemRolesService extends Serializable {

	List<SystemRole> getSystemRoles();

	List<SystemRole> getExclusiveSystemRoles(User u);

}