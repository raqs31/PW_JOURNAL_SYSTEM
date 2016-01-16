package pw.mario.faces.admin.co;

import static pw.mario.faces.common.util.Messages.addAccessDeniedMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.admin.api.impl.EditableUserPickListRoles;
import pw.mario.faces.common.action.Action;
import pw.mario.faces.common.action.form.ConfirmWarning;
import pw.mario.faces.common.action.form.OnConfirmAction;
import pw.mario.faces.common.api.PickListRoles;
import pw.mario.faces.common.exception.PerformActionException;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.service.DepartmentService;
import pw.mario.journal.service.SystemRolesService;
import pw.mario.journal.service.UserService;

@Named
@ViewScoped
@NoArgsConstructor
@Log4j
public class EditUserController implements Serializable {
	private static final long serialVersionUID = 4310678656945508259L;

	@Inject
	private UserService userService;
	@Inject
	private SystemRolesService sysRolesService;
	@Inject
	private DepartmentService deptService;

	@Getter
	@Setter
	private User editUser;

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
	@Getter
	@Setter
	private OnConfirmAction confirmDeleteAction;

	@PostConstruct
	private void init() {
		allSystemRoles = sysRolesService.getSystemRoles();
		exclusiveSystemRoles = new LinkedList<>();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request.getParameter("userId") != null) {
			editUser = userService.getUser(Long.parseLong(request.getParameter("userId")));
			userService.loadDetails(editUser);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Nie przekazano użytkownika do aktualizacji", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("users");
			} catch (IOException e) {
				log.error("Error while redirect", e);
				throw new RuntimeException(e);
			}
			return;
		}
		departments = deptService.getActiveDepartmentList();
		userRoles = new EditableUserPickListRoles(sysRolesService.getExclusiveSystemRoles(editUser),
				editUser.getSystemRoles());

		confirmDeleteAction = ConfirmWarning
								.builder()
								.message("Czy na pewno chcesz usunąć użytkownika " + editUser.getLogin())
								.action(new DeleteAction())
								.buttonValue("Usuń użytkownika")
								.build();
	}

	public void updateUser() {
		try {
			editUser.setSystemRoles(userRoles.getPickListSystemRoles().getTarget());
			editUser = userService.updateUser(editUser);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano użytkownika",
					editUser.getLogin());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (EJBAccessException ejbEx) {
			addAccessDeniedMessage();
		}
	}
	
	private class DeleteAction implements Action {
		@Override
		public void doAction() throws PerformActionException {
			FacesContext ctx = FacesContext.getCurrentInstance();

			try {
				userService.deleteUser(editUser);

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto użytkownika", editUser.getLogin());
				ctx.getExternalContext().getFlash().setKeepMessages(true);
				ctx.addMessage(null, msg);
			} catch (Exception ex) {
				log.error("Error while delete users", ex);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd podczas usuwania użytkownika", null);
				ctx.addMessage(null, msg);
				throw new PerformActionException();
			}
		}
	}
}
