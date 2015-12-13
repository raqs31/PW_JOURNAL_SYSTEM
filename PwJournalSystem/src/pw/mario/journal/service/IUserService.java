package pw.mario.journal.service;

import java.util.List;

import pw.mario.journal.model.User;

public interface IUserService {
	List<User> getUserList();
	
	User createUser(User u);
}
