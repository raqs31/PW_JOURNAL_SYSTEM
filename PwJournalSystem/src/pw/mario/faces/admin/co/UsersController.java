package pw.mario.faces.admin.co;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.User;
import pw.mario.journal.service.DepartmentService;
import pw.mario.journal.service.SystemRolesService;
import pw.mario.journal.service.UserService;

@ManagedBean(name="usersController")
@ViewScoped
@Log4j
@Data
@NoArgsConstructor
public class UsersController {
	@Inject private UserService userService;
	@Inject private SystemRolesService sysRolesService;
	@Inject private DepartmentService deptService;
	
	@Getter @Setter private List<User> users;
	@Getter @Setter private List<Department> departments;
	@Getter @Setter private User selectedUser;
	
	@PostConstruct
	private void init() {
		users = userService.getUserList();
		departments = deptService.getActiveDepartmentList();
	}
	
	public void onUserEdit(RowEditEvent e) {
		User u = (User)e.getObject();
		try {
		userService.updateUser(u);
		} catch (Exception ex) {
			log.error("error in update", ex);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie udało się zaktualizować użytkownika" + u.getLogin(), ex.getMessage())
						);
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano użytkownika", u.getLogin())
				);
		
	}
}
