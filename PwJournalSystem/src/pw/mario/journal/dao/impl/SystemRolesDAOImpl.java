package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.TypedQuery;

import pw.mario.journal.dao.SystemRolesDAO;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;

@Default
@Dependent
public class SystemRolesDAOImpl extends AbstractDAOImpl<SystemRole> implements SystemRolesDAO {
	/* (non-Javadoc)
	 * @see pw.mario.journal.dao.impl.SystemRolesDAO#getSystemRolesList()
	 */
	@Override
	public List<SystemRole> getSystemRolesList() {
		return em.createQuery("from SystemRole", SystemRole.class).getResultList();
	}

	/* (non-Javadoc)
	 * @see pw.mario.journal.dao.impl.SystemRolesDAO#getExcludedSystemRoles(pw.mario.journal.model.User)
	 */
	@Override
	public List<SystemRole> getExcludedSystemRoles(User u) {
		TypedQuery<SystemRole> q = em.createNamedQuery("SystemRole.excludedRoles", SystemRole.class);
		q.setParameter("id", u.getId());
		
		return q.getResultList();
		
	}
}
