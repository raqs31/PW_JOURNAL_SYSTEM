package pw.mario.journal.service.common.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.common.DepartmentDAO;
import pw.mario.journal.model.common.Department;
import pw.mario.journal.service.common.DepartmentService;

@NoArgsConstructor
@Stateless
@Transactional
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
	@Transactional(value=TxType.REQUIRED)
	@RolesAllowed("ADMIN")
	public void saveDepartment(Department d) {
		deptDao.addDepartment(d);
	}

	@Override
	public List<Department> getActiveDepartmentsNonPersis() {
		return null;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	@RolesAllowed("ADMIN")
	public void deleteDepartment(Department d) {
		deptDao.removeDepartment(d);
	}

	@Override
	public Department getDepartment(String deptCode) {
		return deptDao.getDepartment(deptCode);
	}

}
