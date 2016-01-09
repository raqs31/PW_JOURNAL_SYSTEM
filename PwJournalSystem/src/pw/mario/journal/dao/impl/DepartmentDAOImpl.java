package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.Query;

import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;

@Default
@Dependent
@SuppressWarnings("unchecked")
public class DepartmentDAOImpl extends AbstractDAOImpl<Department> implements DepartmentDAO {

	public List<Department> getAllDepartments() {
		return em.createQuery("select d from Department d").getResultList();
	}

	@Override
	public List<Department> getActiveDepartment() {
		Query q = em.createQuery("from Department d where d.isActive = ?1");
		q.setParameter(1, Boolean.TRUE);
		return q.getResultList();
	}

	@Override
	public void deactiveDepartment(Department d) {
		d.setIsActive(false);
	}

	@Override
	public void addDepartment(Department d) {
		persist(d);
	}

	@Override
	public Department getDepartment(Long id) {
		return getReference(id);
	}

	@Override
	public Department getDepartment(String code) {
		return em.createQuery("select d from Department d where d.deptCode = ?1", Department.class)
					.setParameter(1, code)
					.getSingleResult();
	}

	@Override
	public void removeDepartment(Department d) {
		em.remove(reAttachEntity(d));
	}

}
