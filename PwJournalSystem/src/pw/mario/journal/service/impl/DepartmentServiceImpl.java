package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.DepartmentService;

@NoArgsConstructor
@Stateless
public class DepartmentServiceImpl implements DepartmentService {
	private static final long serialVersionUID = 1L;
	@Inject
	private DepartmentDAO deptDao;
	
	@Override
	public List<Department> getDepartmentList() {
		return deptDao.getAllDepartments();
	}

	@Override
	public List<Department> getActiveDepartmentList() {
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
