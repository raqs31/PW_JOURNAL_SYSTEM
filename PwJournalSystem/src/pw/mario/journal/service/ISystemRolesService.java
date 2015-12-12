package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;

public interface ISystemRolesService {

	List<SystemRole> getSystemRoles();

	List<SystemRole> getExclusiveSystemRoles(User u);

}