package pw.mario.faces.admin.co;

import static pw.mario.faces.common.util.AccessDenied.addAccessDeniedMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.faces.api.PickListRoles;
import pw.mario.faces.api.action.OnConfirmAction;
import pw.mario.faces.api.impl.EditableUserPickListRoles;
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
	
	@Inject private UserService userService;
	@Inject private SystemRolesService sysRolesService;
	@Inject private DepartmentService deptService;
	
	@Getter @Setter private User editUser;

	@Getter @Setter private List<SystemRole> allSystemRoles;		
	@Getter @Setter private List<SystemRole> exclusiveSystemRoles;
	@Getter @Setter private List<Department> departments;
	@Getter @Setter private PickListRoles userRoles;
	@Getter @Setter private ConfirmDeleteAction confirmDeleteAction;

	
	
	@PostConstruct
	private void init() {
		allSystemRoles = sysRolesService.getSystemRoles();
		exclusiveSystemRoles = new LinkedList<>();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request.getParameter("userId") != null) {
			editUser = userService.getUser(Long.parseLong(request.getParameter("userId")));
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nie przekazano użytkownika do aktualizacji", null);
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
		userRoles = new EditableUserPickListRoles(sysRolesService.getExclusiveSystemRoles(editUser), editUser.getSystemRoles());
		confirmDeleteAction = new ConfirmDeleteAction();
	}
	
	public void updateUser() {
		try {
			editUser.setSystemRoles(userRoles.getPickListSystemRoles().getTarget());
			editUser = userService.updateUser(editUser);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano użytkownika", editUser.getLogin());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch(EJBAccessException ejbEx) {
			addAccessDeniedMessage();
		}
	}

	
	
	private class ConfirmDeleteAction implements OnConfirmAction {
		@Override
		public void doAction() {
			FacesContext ctx = FacesContext.getCurrentInstance();
			
			try {
				userService.deleteUser(editUser);
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto użytkownika", editUser.getLogin());
				ctx.getExternalContext().getFlash().setKeepMessages(true);
				ctx.addMessage(null, msg);
				ctx.getExternalContext().redirect("users.xhtml");
			} catch (IOException e) {
				log.error("Error while redirect", e);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd podczas przekierowania", null);
				ctx.addMessage(null, msg);
			} catch (Exception ex) {
				log.error("Error while delete users", ex);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd podczas usuwania użytkownika", null);
				ctx.addMessage(null, msg);
			}
		}

		@Override
		public String getHeader() {
			return "Potwierdzenie";
		}

		@Override
		public String getMessage() {
			return "Czy na pewno chcesz usunąć użytkownika " + editUser.getLogin();
		}

		@Override
		public String getIcon() {
			return "ui-icon-alert";
		}

		@Override
		public String buttonValue() {
			return "Usuń użytkownika";
		}
	}
}
