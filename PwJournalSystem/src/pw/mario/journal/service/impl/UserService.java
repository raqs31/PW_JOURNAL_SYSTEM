package pw.mario.journal.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import lombok.NoArgsConstructor;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;
import pw.mario.journal.service.IUserService;

@NoArgsConstructor
@Stateless
public class UserService implements IUserService {
	@Inject private UserDAO userDao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<User> getUserList() {
		List<User> u = userDao.getUsersList();
		System.out.println("CHUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUJ");
		for (User uu : u)
			System.out.println(uu);
		return userDao.getUsersList();
	}


}
