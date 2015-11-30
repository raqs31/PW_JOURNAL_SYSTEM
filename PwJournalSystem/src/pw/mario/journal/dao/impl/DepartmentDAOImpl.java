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

	@Override
	public List<Department> getAllDepartments() {
		return em.createQuery("select d from Department d").getResultList();
	}

	@Override
	public List<Department> getActiveDepartment() {
		Query q = em.createQuery("from Department d where d.isActive = :1");
		q.setParameter(1, Boolean.TRUE);
		return q.getResultList();
	
	}

	@Override
	public void deactiveDepartment(Department d) {
		//TODO dodać sprawdzenie czy zaden uzytkownik nie znajduje sie dalej w departamencie
		d.setIsActive(false);
	}

	@Override
	public void addDepartment(Department d) {
		persist(d);
	}

}
