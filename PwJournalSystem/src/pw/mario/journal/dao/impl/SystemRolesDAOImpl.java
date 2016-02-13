package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.TypedQuery;

import org.hibernate.criterion.Restrictions;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.SystemRolesDAO;
import pw.mario.journal.model.common.SystemRole;
import pw.mario.journal.model.common.User;

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

	@Override
	public List<SystemRole> getUserSystemRoles(User u) {
		System.out.println(u.getId());
		List<SystemRole> roles = session().createCriteria(SystemRole.class).createCriteria("users").add(Restrictions.idEq(u.getId())).list();
		return roles;
	}

	@Override
	public SystemRole getSystemRole(String name) {
		return (SystemRole) session().createCriteria(SystemRole.class).add(Restrictions.eq("roleName", name)).uniqueResult();
	}
}
