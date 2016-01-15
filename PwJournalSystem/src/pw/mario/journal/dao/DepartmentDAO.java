package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Department;

public interface DepartmentDAO {
	List<Department> getAllDepartments();

	List<Department> getActiveDepartment();

	void deactiveDepartment(Department d);

	void addDepartment(Department d);

	Department getDepartment(Long id);

	Department getDepartment(String code);

	void removeDepartment(Department d);
}
