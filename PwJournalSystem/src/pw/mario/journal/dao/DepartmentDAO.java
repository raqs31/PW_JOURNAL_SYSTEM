package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Department;

public interface DepartmentDAO {
	public List<Department> getAllDepartments();
	
	public List<Department> getActiveDepartment();
	
	public void deactiveDepartment(Department d);
	
	public void addDepartment(Department d);
	
	public Department getDepartment(Long id);
	
	public Department getDepartment(String code);
	
	public void removeDepartment(Department d);
}
