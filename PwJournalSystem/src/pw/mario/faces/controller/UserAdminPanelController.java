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
import pw.mario.journal.model.User;
import pw.mario.journal.service.IUserService;

@ManagedBean(name="userAdminPanelCo")
@ViewScoped
@NoArgsConstructor
public class UserAdminPanelController {
	private static final Logger log = Logger.getLogger(UserAdminPanelController.class);
	@Inject private IUserService userService;
	
	@Getter @Setter private IUserList userList;
	
	@PostConstruct
	private void init() {
		this.userList = new UserList();
		log.debug("##########################\n##########################\n##########################\n##########################\n");
	}
	
	@NoArgsConstructor
	private class UserList implements IUserList {
		@Getter @Setter private List<User> users;
		@Setter private boolean readOnly;
		
		@Override
		public boolean getReadOnly() {
			return readOnly;
		}
		
		@PostConstruct
		private void init() {
			log.debug("Init guano ##########################\n##########################\n##########################\n##########################\n");
			readOnly = false;
			users = userService.getUserList();
		}
	}
}
