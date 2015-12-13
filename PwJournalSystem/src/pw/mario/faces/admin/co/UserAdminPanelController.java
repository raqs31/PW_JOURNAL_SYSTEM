package pw.mario.faces.admin.co;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.faces.api.IUserList;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.service.IDepartmentService;
import pw.mario.journal.service.ISystemRolesService;
import pw.mario.journal.service.IUserService;

@ManagedBean(name = "userAdminPanelCo")
@ViewScoped
@NoArgsConstructor
public class UserAdminPanelController implements Serializable {
	private static final long serialVersionUID = 4310678656945508259L;
	private static final Logger log = Logger.getLogger(UserAdminPanelController.class);
	
	@Inject private IUserService userService;
	@Inject private ISystemRolesService sysRolesService;
	@Inject private IDepartmentService deptService;
	
	@Getter @Setter private IUserList userList;
	@Getter @Setter private List<User> users;
	@Getter @Setter private User newUser;

	@Getter @Setter private List<SystemRole> allSystemRoles;
	@Getter @Setter private List<SystemRole> exclusiveSystemRoles;
	@Getter @Setter private List<Department> departments;
	
	
	@PostConstruct
	private void init() {
		this.userList = new UserList();
		userList.setUsers(userService.getUserList());
		allSystemRoles = sysRolesService.getSystemRoles();
		exclusiveSystemRoles = sysRolesService.getExclusiveSystemRoles(userList.getUsers().get(0));
		newUser = new User();
		departments = deptService.getActiveDepartmentList();
	}

	@NoArgsConstructor
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private class UserList implements IUserList {
		private static final long serialVersionUID = -6930829924611447508L;
		@Getter @Setter private List<User> users;
		@Setter private boolean readOnly;
		@Getter @Setter private User selectedUser;
		
		@Override
		public boolean getReadOnly() {
			return readOnly;
		}

		@Override
		public void onRowSelect(SelectEvent e) {
			FacesMessage msg = new FacesMessage("Wybrano użytkownika", ((User)e.getObject()).getLogin());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		@Override
		public void onRowUnselect(UnselectEvent e) {
			FacesMessage msg = new FacesMessage("Odznaczono użytkownika", e.getObject().toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
