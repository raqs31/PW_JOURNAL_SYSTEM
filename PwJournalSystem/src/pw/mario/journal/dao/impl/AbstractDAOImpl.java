package pw.mario.journal.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import pw.mario.journal.model.ext.IdTable;


public abstract class AbstractDAOImpl<T extends IdTable> {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
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
		em.remove(reAttachEntity(o));
	}
	
	public TypedQuery<T> createTypedQuery(String query) {
		return em.createQuery(query, clazz); 
	}
	
	public TypedQuery<T> createNamedTypedQuery(String namedQuery) {
		return em.createNamedQuery(namedQuery, clazz);
	}
	
	public T reAttachEntity(T o) {
		if (!em.contains(o))
			o = em.find(clazz, o.getId());
		return o;
	}
}
