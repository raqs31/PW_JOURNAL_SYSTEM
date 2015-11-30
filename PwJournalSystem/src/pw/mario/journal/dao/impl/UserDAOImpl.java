package pw.mario.journal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.Query;

import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.User;

@Default
@Dependent
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

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
