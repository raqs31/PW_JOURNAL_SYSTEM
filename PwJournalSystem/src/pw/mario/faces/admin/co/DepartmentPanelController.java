package pw.mario.faces.admin.co;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.IDepartmentService;

@ManagedBean(name="deptPaneCo")
@ViewScoped
@RolesAllowed({"ADMIN"})
public class DepartmentPanelController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
