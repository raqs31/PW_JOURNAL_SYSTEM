package pw.mario.journal.service.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;
import pw.mario.journal.service.UserService;
import pw.mario.journal.util.MD5Passwd;

@Log4j
@NoArgsConstructor
@Stateless
@Transactional
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

	@Override
	public User getUserByLogin(String login) {
		try {
			return userDao.getUserByLogin(login);
		} catch (NoResultException e) {
			log.warn("Cannot find user with login: " + login);
		}
		return null;
	}


}
