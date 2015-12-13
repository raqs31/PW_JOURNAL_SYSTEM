package pw.mario.journal.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;


public abstract class AbstractDAOImpl<T> {
	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> clazz;
	
	{
		clazz = (Class) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
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
	
	public TypedQuery<T> createTypedQuery(String query) {
		return em.createQuery(query, clazz); 
	}
	
	public TypedQuery<T> createNamedTypedQuery(String namedQuery) {
		return em.createNamedQuery(namedQuery, clazz);
	}
}
