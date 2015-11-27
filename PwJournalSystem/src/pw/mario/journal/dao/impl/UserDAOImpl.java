package pw.mario.journal.dao.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;

@Singleton
public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersList() {
		Query q = em.createQuery("Select u from User u");
		return q.getResultList();
	}

	@Override
	public void addUser(User u) {
		em.persist(u);
	}

	@Override
	public void deleteUser(User u) {
		em.remove(u);
	}

	@Override
	public void updateUser(User u) {
		em.merge(u);
	}

}
