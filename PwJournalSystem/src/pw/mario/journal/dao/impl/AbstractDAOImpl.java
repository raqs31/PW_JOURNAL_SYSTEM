package pw.mario.journal.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractDAOImpl<T> {
	@PersistenceContext
	protected EntityManager em;
	
	protected void beginTransaction() {
		em.getTransaction().begin();
	}
	
	public void commit() {
		em.getTransaction().commit();
	}
	
	public void rollback() {
		em.getTransaction().rollback();
	}
	
	public void persist(T o) {
		em.persist(o);
	}
	
	public void delete(T o) {
		em.remove(o);
	}
	
}
