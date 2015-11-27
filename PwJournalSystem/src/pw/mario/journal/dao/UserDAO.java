package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.User;

public interface UserDAO {
	public List<User> getUsersList();
	
	public void addUser(User u);
	
	public void deleteUser(User u);
	
	public void updateUser(User u);
}
