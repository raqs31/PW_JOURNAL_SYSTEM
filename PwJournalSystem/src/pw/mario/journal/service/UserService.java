package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.User;

public interface UserService {
	List<User> getUserList();
	
	User createUser(User u);
	
	User updateUser(User u);
	
	User getUser(long id);
	
	void deleteUser(User u);
}