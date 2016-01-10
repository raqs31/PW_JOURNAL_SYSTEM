package pw.mario.faces.admin.co;

import static pw.mario.faces.common.util.Messages.addAccessDeniedMessage;
import static pw.mario.faces.common.util.Messages.addMessage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.faces.common.action.Action;
import pw.mario.faces.common.action.ConfirmWarning;
import pw.mario.faces.common.action.OnConfirmAction;
import pw.mario.journal.model.Department;
import pw.mario.journal.service.DepartmentService;

@Named("deptPaneCo")
@ViewScoped
@NoArgsConstructor
public class DepartmentPanelController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private DepartmentService deptService;
	
	@Getter @Setter private List<Department> departmentsList;
	@Getter @Setter private List<Department> filteredDepartments;
	@Getter @Setter private List<Department> selectedDepts;
	@Getter @Setter private Department newDept;
	@Getter private OnConfirmAction deleteAction;
	
	@PostConstruct
	private void init() {
		departmentsList = deptService.getDepartmentList();
		newDept = new Department();
		ConfirmWarning confirm = new ConfirmWarning();
		confirm.setButtonValue("Usuń wybrane");
		confirm.setAction(new DeleteSelectedDepartmentsAction());
		confirm.setMessage("Usuniętp wybrame departamenty");
		deleteAction = confirm;
	}
	
	public void addDepartment() {
		try {
			deptService.saveDepartment(newDept);
			departmentsList.add(newDept);
			newDept = new Department();
			addMessage(FacesMessage.SEVERITY_INFO, "Utworzono departament", newDept.getFullName());
		} catch(EJBAccessException ejbEx) {
			addAccessDeniedMessage();
		}
	}
	
	private class DeleteSelectedDepartmentsAction implements Action {
		@Override
		public void doAction() {
			if (selectedDepts == null || selectedDepts.size() == 0)
				addMessage(FacesMessage.SEVERITY_WARN, "Nie wybrano departamentów", null);
			else {
				try {
					for (Department d: selectedDepts) {
						departmentsList.remove(d);
						deptService.deleteDepartment(d);
					}
					addMessage(FacesMessage.SEVERITY_INFO, "Usunięto wybrane departamenty", null);
				} catch(EJBAccessException ejEx) {
					addAccessDeniedMessage();
				}
			}
		}
	}
}
