package pw.mario.faces.admin.co;

import static pw.mario.common.util.Messages.addAccessDeniedMessage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.journal.model.common.Department;
import pw.mario.journal.model.common.User;
import pw.mario.journal.service.common.DepartmentService;
import pw.mario.journal.service.common.UserService;

@Log4j
@NoArgsConstructor
@Named
@ViewScoped
public class UsersController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	@Inject
	private DepartmentService deptService;
	@Getter
	@Setter
	private List<User> users;
	@Getter
	@Setter
	private List<Department> departments;
	@Getter
	@Setter
	private User selectedUser;

	@PostConstruct
	private void init() {
		users = userService.getUserList();
		departments = deptService.getActiveDepartmentList();
	}

	public void onUserEdit(RowEditEvent e) {
		User u = (User) e.getObject();
		try {
			userService.updateUser(u);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano użytkownika", u.getLogin()));
		} catch(EJBAccessException ejbEx) {
			addAccessDeniedMessage();
		} catch (Exception ex) {
			log.error("error in update", ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nie udało się zaktualizować użytkownika" + u.getLogin(), ex.getMessage()));
		}
	}
	public void onRowToggle(ToggleEvent event) {
		User u = (User)event.getData();
		userService.loadDetails(u);
	}
}
