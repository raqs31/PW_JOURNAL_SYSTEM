package pw.mario.journal.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import pw.mario.journal.model.IdTable;

@SuppressWarnings("rawtypes")
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
	
	protected void commit() {
		em.getTransaction().commit();
	}
	
	protected void rollback() {
		em.getTransaction().rollback();
	}
	
	protected void persist(T o) {
		em.persist(o);
		em.flush();
	}
	
	protected void delete(T o) {
		em.remove(reAttachEntity(o));
	}
	
	protected TypedQuery<T> createTypedQuery(String query) {
		return em.createQuery(query, clazz); 
	}
	
	protected TypedQuery<T> createNamedTypedQuery(String namedQuery) {
		return em.createNamedQuery(namedQuery, clazz);
	}
	
	protected T reAttachEntity(T o) {
		if (!em.contains(o))
			o = em.find(clazz, o.getId());
		return o;
	}
	
	protected T getReference(Object id) {
		return em.getReference(clazz, id);
	}
	
	protected T find(Object id) {
		return em.find(clazz, id);
	}
	
	protected T merge(T o) {
		return em.merge(o);
	}
	
	protected Session session() {
		return em.unwrap(Session.class);
	}
	
	protected T locked(Object id, LockModeType lock) {
		T obj = em.find(clazz, id);
		em.refresh(obj);
		em.lock(obj, lock);
		return obj;
	}
}
