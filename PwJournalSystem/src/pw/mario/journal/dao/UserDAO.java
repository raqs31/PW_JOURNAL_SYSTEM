package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.Department;
import pw.mario.journal.model.User;

public interface UserDAO {
	List<User> getUsersList();

	void addUser(User u);

	void deleteUser(User u);

	User updateUser(User u);

	User getUserByEmail(User u);

	User getUserByLogin(User u);

	User getUserByEmail(String email);

	User getUserByLogin(String login);

	User getUser(long id);

	List<User> getUsers(Department d);
	
	List<User> getUsers(Department d, String sr);
}
