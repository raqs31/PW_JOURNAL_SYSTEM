package pw.mario.journal.service;

import java.io.Serializable;
import java.util.List;

import pw.mario.journal.model.common.Department;
import pw.mario.journal.model.common.User;

public interface UserService extends Serializable {
	List<User> getUserList();
	
	User createUser(User u);
	
	User updateUser(User u);
	
	User getUser(long id);
	
	void deleteUser(User u);
	
	User getUserByLogin(String login);
	
	void loadDetails(User u);
	
	List<User> getUsersFromDepartment(Department d);
	
	List<User> getUsers(Department d, String systemRole);
}
