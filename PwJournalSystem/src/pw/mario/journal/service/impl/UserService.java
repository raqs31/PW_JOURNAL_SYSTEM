package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;
import pw.mario.journal.service.IUserService;
import pw.mario.journal.util.MD5Passwd;

@NoArgsConstructor
@Stateless
public class UserService implements IUserService {
	@Inject private UserDAO userDao;
	@Inject private MD5Passwd md5Passwd;
	
	@Override
	public List<User> getUserList() {
		return userDao.getUsersList();
	}

	@Override
	public User createUser(User u) {
		if (u == null)
			throw new NullPointerException();
		
		u.setPasswd(md5Passwd.generate(u.getPasswd()));
		
		//TODO validate
		
		userDao.addUser(u);
		return u;
	}


}