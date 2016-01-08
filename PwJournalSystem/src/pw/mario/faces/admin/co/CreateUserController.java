package pw.mario.faces.admin.co;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.faces.admin.api.impl.EditableUserPickListRoles;
import pw.mario.faces.common.api.PickListRoles;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.service.DepartmentService;
import pw.mario.journal.service.SystemRolesService;
import pw.mario.journal.service.UserService;

@Named
@ViewScoped
@NoArgsConstructor
public class CreateUserController implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	@Inject
	private SystemRolesService sysRolesService;
	@Inject
	private DepartmentService deptService;
	@Getter
	@Setter
	private User newUser;
	@Getter
	@Setter
	private List<SystemRole> allSystemRoles;
	@Getter
	@Setter
	private List<SystemRole> exclusiveSystemRoles;
	@Getter
	@Setter
	private List<Department> departments;
	@Getter
	@Setter
	private PickListRoles userRoles;

	@PostConstruct
	private void init() {
		allSystemRoles = sysRolesService.getSystemRoles();
		exclusiveSystemRoles = new LinkedList<>();

		newUser = new User();
		departments = deptService.getActiveDepartmentList();
		userRoles = new EditableUserPickListRoles(sysRolesService.getSystemRoles(),
				new LinkedList<>());
	}

	public void createUser() {
		newUser.setSystemRoles(userRoles.getPickListSystemRoles().getTarget());
		userService.createUser(newUser);

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Utworzono użytkownika", newUser.getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		newUser = new User();
		userRoles.setPickListSystemRoles(new DualListModel<>(sysRolesService.getSystemRoles(), new LinkedList<>()));
	}
}
