package pw.mario.journal.service.common;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.common.Department;

public interface DepartmentService extends Serializable {
	public List<Department> getDepartmentList();

	public List<Department> getActiveDepartmentList();
	
	public void saveDepartment(Department d);
	
	public List<Department> getActiveDepartmentsNonPersis();
	
	public void deleteDepartment(Department d);
	
	public Department getDepartment(String deptCode);
}
