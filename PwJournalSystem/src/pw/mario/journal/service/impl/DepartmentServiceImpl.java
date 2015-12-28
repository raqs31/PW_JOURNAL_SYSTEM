package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.DepartmentService;

@Stateless
public class DepartmentServiceImpl implements DepartmentService {
	@Inject
	private DepartmentDAO deptDao;
	
	@Override
	public List<Department> getDepartmentList() {
		return deptDao.getAllDepartments();
	}

	@Override
	public List<Department> getActiveDepartmentList() {
		// TODO Auto-generated method stub
		return deptDao.getActiveDepartment();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveDepartment(Department d) {
		deptDao.addDepartment(d);
	}

	@Override
	public List<Department> getActiveDepartmentsNonPersis() {
		// TODO Auto-generated method stub
		return null;
	}

}
