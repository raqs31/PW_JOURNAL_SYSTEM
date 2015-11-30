package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.transaction.Transactional;

import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.IDepartmentService;

@Stateless
public class DepartmentService implements IDepartmentService {
	@Inject
	private DepartmentDAO deptDao;
	
	@Override
	public List<Department> getDepartmentList() {
		return deptDao.getAllDepartments();
	}

	@Override
	public List<Department> getActiveDepartmentList() {
		// TODO Auto-generated method stub
		return getActiveDepartmentList();
	}

	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void saveDepartment(Department d) {
		deptDao.addDepartment(d);
	}

}
