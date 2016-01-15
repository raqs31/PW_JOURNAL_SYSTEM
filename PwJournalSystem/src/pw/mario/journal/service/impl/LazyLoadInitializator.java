package pw.mario.journal.service.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LazyLoadInitializator implements pw.mario.journal.service.LazyLoadInitializator, Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void initialize(Object o) {
		Hibernate.initialize(o);
	}

	@Override
	public void refresh(Object o) {
		em.refresh(o);
	}
	
}
