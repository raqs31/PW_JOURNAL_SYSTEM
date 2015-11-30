package pw.mario.faces.controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.IDepartmentService;

@ManagedBean("deptPaneCo")
@ViewScoped
@RolesAllowed({"ADMIN"})
public class DepartmentPanelController {
	@Inject private IDepartmentService deptService;
	
	@Getter @Setter private List<Department> departmentsList;
	@Getter @Setter private List<Department> filteredDepartments;
	@Getter @Setter private Department newDept;
	
	@PostConstruct
	private void init() {
		departmentsList = deptService.getDepartmentList();
		newDept = new Department();
	}
	
	public void addDepartment() {
		deptService.saveDepartment(newDept);
		departmentsList.add(newDept);
		newDept = new Department();
		
	}
}
