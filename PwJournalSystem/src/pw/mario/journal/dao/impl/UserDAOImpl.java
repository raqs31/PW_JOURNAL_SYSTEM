package pw.mario.journal.dao.impl;

import static pw.mario.journal.model.User.Queries.GET_BY_EMAIL;
import static pw.mario.journal.model.User.Queries.GET_BY_LOGIN;
import static pw.mario.journal.model.User.Queries.USERS_WITH_DEPARTMENT_ROLE;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.persistence.Query;

import pw.mario.journal.dao.AbstractDAOImpl;
import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.Department;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.User;
import pw.mario.journal.model.User.Queries;

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
		persist(u);
	}

	@Override
	public void deleteUser(User u) {
		delete(u);
	}

	@Override
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
		return createNamedTypedQuery(GET_BY_EMAIL)
				.setParameter(1, email.toUpperCase())
				.getSingleResult();
	}

	@Override
	public User getUserByLogin(String login) {
		return createNamedTypedQuery(GET_BY_LOGIN)
				.setParameter(1, login.toUpperCase())
				.getSingleResult();
	}

	@Override
	public User getUser(long id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> getUsers(Department d) {
		if (d == null)
			return getUsersList();
		return createTypedQuery("select u from User u where u.userId = ?1").setParameter(1, d == null ? null : d.getId()).getResultList();
	}

	@Override
	public List<User> getUsers(Department d, String sr) {
		return createNamedTypedQuery(USERS_WITH_DEPARTMENT_ROLE).setParameter(1, d == null ? "" : d.getId()).setParameter(2, sr).getResultList();
	}

	@Override
	public List<User> getAvailableAcceptors(Long articleId) {
		return getNotCrossedUsers(articleId, SystemRole.Roles.ACCEPTOR);
	}

	@Override
	public List<User> getAvailableManagers(Long articleId) {
		return getNotCrossedUsers(articleId, SystemRole.Roles.MANAGER);
	}

	
	private List<User> getNotCrossedUsers(Long articleId, String roleName) {
		return createNamedTypedQuery(Queries.USERS_NOT_CROSSED_AUTHORS)
				.setParameter(1, articleId)
				.setParameter(2, roleName)
				.getResultList();
	}
}
