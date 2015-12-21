package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pw.mario.journal.dao.SystemRolesDAO;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.service.SystemRolesService;

@Stateless
public class SystemRolesServiceImpl implements SystemRolesService {
	@Inject
	private  SystemRolesDAO sysRolesDao;
	
	
	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ISystemRolesServie#getSystemRoles()
	 */
	@Override
	public List<SystemRole> getSystemRoles() {
		return sysRolesDao.getSystemRolesList();
	}
	
	/* (non-Javadoc)
	 * @see pw.mario.journal.service.impl.ISystemRolesServie#getExclusiveSystemRoles(pw.mario.journal.model.User)
	 */
	@Override
	public List<SystemRole> getExclusiveSystemRoles(User u) {
		return sysRolesDao.getExcludedSystemRoles(u);
	}
}
