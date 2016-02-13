package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.common.SystemRole;
import pw.mario.journal.model.common.User;

public interface SystemRolesDAO {

	List<SystemRole> getSystemRolesList();

	List<SystemRole> getExcludedSystemRoles(User u);
	
	List<SystemRole> getUserSystemRoles(User u);
	
	SystemRole getSystemRole(String name);
}