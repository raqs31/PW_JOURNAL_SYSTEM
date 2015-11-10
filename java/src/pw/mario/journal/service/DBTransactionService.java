package pw.mario.journal.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name="dbSession", eager=false)
@SessionScoped
public class DBTransactionService {
	private final EntityManager em;
	
	public DBTransactionService() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW_JOURNAL_SYSTEM");
		em = emf.createEntityManager();
	}
	public EntityManager getEm() {return em;}
}
