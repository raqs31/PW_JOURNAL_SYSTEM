package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.Department;

public interface IDepartmentService {
	public List<Department> getDepartmentList();

	public List<Department> getActiveDepartmentList();
	
	public void saveDepartment(Department d);
}
