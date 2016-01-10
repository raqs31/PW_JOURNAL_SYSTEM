package pw.mario.journal.dao.impl;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addUser(User u) {
		persist(u);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUser(User u) {
		delete(u);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User updateUser(User u) {
		em.merge(u);
		return getUser(u.getUserId());
	}

	@Override
	public User getUserByEmail(User u) {
		return getUserByEmail(u.getEmail());
	}

	@Override
	public User getUserByLogin(User u) {
		return getUserByLogin(u.getLogin());
	}

	@Override
	public User getUserByEmail(String email) {
		return createNamedTypedQuery(User.Queries.GET_BY_EMAIL)
				.setParameter(1, email.toUpperCase())
				.getSingleResult();
	}

	@Override
	public User getUserByLogin(String login) {
		return createNamedTypedQuery(User.Queries.GET_BY_LOGIN)
				.setParameter(1, login.toUpperCase())
				.getSingleResult();
	}

	@Override
	public User getUser(long id) {
		return em.find(User.class, id);
	}

}
