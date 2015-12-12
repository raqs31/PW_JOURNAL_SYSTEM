package pw.mario.faces.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.faces.api.IUserList;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.service.ISystemRolesService;
import pw.mario.journal.service.IUserService;

@ManagedBean(name = "userAdminPanelCo", eager = true)
@ViewScoped
@NoArgsConstructor
public class UserAdminPanelController {
	private static final Logger log = Logger.getLogger(UserAdminPanelController.class);
	@Inject
	private IUserService userService;
	@Inject
	private ISystemRolesService sysRolesService;
	
	@Getter
	@Setter
	private IUserList userList;
	@Getter
	@Setter
	private List<User> users;

	@Getter
	@Setter
	private User newUser;
	
	@Getter
	@Setter
	private List<SystemRole> allSystemRoles;
	
	@Getter
	@Setter
	private List<SystemRole> exclusiveSystemRoles;
	
	
	@PostConstruct
	private void init() {
		this.userList = new UserList();
		userList.setUsers(userService.getUserList());
		allSystemRoles = sysRolesService.getSystemRoles();
		exclusiveSystemRoles = sysRolesService.getExclusiveSystemRoles(userList.getUsers().get(0));
	}

	@NoArgsConstructor
	private class UserList implements IUserList {
		private static final long serialVersionUID = -6930829924611447508L;
		private List<User> users;
		private boolean readOnly;

		@Override
		public boolean getReadOnly() {
			return readOnly;
		}

		@Override
		public List<User> getUsers() {
			return users;
		}

		@Override
		public void setUsers(List<User> users) {
			this.users = users;
		}

		@Override
		public void setReadOnly(boolean isReadOnly) {
			this.readOnly = isReadOnly;
		}
	}
}
