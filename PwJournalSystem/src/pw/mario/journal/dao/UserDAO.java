package pw.mario.journal.dao;

import java.util.List;

import pw.mario.journal.model.User;

public interface UserDAO {
	public List<User> getUsersList();
	
	public void addUser(User u);
	
	public void deleteUser(User u);
	
	public User updateUser(User u);
	
	public User getUserByEmail(User u);
	
	public User getUserByLogin(User u);
	
	public User getUserByEmail(String email);
	
	public User getUserByLogin(String login);
	
	public User getUser(long id);
}
