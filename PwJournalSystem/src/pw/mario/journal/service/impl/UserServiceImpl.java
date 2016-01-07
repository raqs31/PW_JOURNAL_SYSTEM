package pw.mario.journal.service.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;
import pw.mario.journal.service.UserService;
import pw.mario.journal.util.MD5Passwd;

@NoArgsConstructor
@Stateless
public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	@Inject private UserDAO userDao;
	@Inject private MD5Passwd md5Passwd;
	
	@Override
	public List<User> getUserList() {
		return userDao.getUsersList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@RolesAllowed("ADMIN")
	public User createUser(User u) {
		if (u == null)
			throw new NullPointerException();
		
		u.setPasswd(md5Passwd.generate(u.getPasswd()));
		
		userDao.addUser(u);
		return u;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@RolesAllowed("ADMIN")
	public User updateUser(User u) {
		return userDao.updateUser(u);
	}
	
	@Override
	public User getUser(long id) {
		return userDao.getUser(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@RolesAllowed("ADMIN")
	public void deleteUser(User u) {
		userDao.deleteUser(u);
	}


}
