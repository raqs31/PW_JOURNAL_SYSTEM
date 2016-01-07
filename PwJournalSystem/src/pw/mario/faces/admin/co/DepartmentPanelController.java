package pw.mario.faces.admin.co;

import static pw.mario.faces.common.util.AccessDenied.addAccessDeniedMessage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.DepartmentService;

@Named("deptPaneCo")
@ViewScoped
public class DepartmentPanelController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private DepartmentService deptService;
	
	@Getter @Setter private List<Department> departmentsList;
	@Getter @Setter private List<Department> filteredDepartments;
	@Getter @Setter private Department newDept;
	
	@PostConstruct
	private void init() {
		departmentsList = deptService.getDepartmentList();
		newDept = new Department();
	}
	
	public void addDepartment() {
		try {
			deptService.saveDepartment(newDept);
			departmentsList.add(newDept);
			newDept = new Department();
		} catch(EJBAccessException ejbEx) {
			addAccessDeniedMessage();
		}
	}
}
