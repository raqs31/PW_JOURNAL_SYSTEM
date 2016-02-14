package pw.mario.journal.service.common.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Hibernate;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import pw.mario.common.util.MD5Passwd;
import pw.mario.journal.dao.common.SystemRolesDAO;
import pw.mario.journal.dao.common.UserDAO;
import pw.mario.journal.model.common.Department;
import pw.mario.journal.model.common.User;
import pw.mario.journal.service.common.UserService;

@Log4j
@NoArgsConstructor
@Stateless
public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	@Inject private UserDAO userDao;
	@Inject private SystemRolesDAO systemRolesDao;
	@Inject private MD5Passwd md5Passwd;
	
	@Override
	public List<User> getUserList() {
		return userDao.getUsersList();
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	@RolesAllowed("ADMIN")
	public User createUser(User u) {
		if (u == null)
			throw new NullPointerException();
		
		u.setPasswd(md5Passwd.generate(u.getPasswd()));
		
		userDao.addUser(u);
		return u;
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	@RolesAllowed("ADMIN")
	public User updateUser(User u) {
		return userDao.updateUser(u);
	}
	
	@Override
	public User getUser(long id) {
		return userDao.getUser(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void loadDetails(User u) {
		if (!Hibernate.isInitialized(u.getSystemRoles()))
			u.setSystemRoles(systemRolesDao.getUserSystemRoles(u));
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<User> getUsersFromDepartment(Department d) {
		return userDao.getUsers(d);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<User> getUsers(Department d, String systemRole) {
		return userDao.getUsers(d, systemRole);
	}
	
	


}
