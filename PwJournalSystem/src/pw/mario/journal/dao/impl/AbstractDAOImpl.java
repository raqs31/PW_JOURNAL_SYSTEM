package pw.mario.journal.dao.impl;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public abstract class AbstractDAOImpl {
	@PersistenceContext
	protected EntityManager em;
	
	protected void beginTransaction() {
		em.getTransaction().begin();
	}
	
	protected void commit() {
		em.getTransaction().commit();
	}
	
	protected void rollback() {
		em.getTransaction().rollback();
	}
	
	protected void persist(Object o) {
		em.persist(o);
	}
	
	protected void delete(Object o) {
		em.remove(o);
	}
	
}
