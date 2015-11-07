package pw.mario.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class UserTest {
	EntityManagerFactory f;
	EntityManager em;
	
	
	@Before
	public void setUp() {
		f = Persistence.createEntityManagerFactory("PW_JOURNAL_SYSTEM");
		em = f.createEntityManager();
		em.getTransaction().commit();
	}
	
	@After
	public void tearDown() {
		em.getTransaction().rollback();
		em.close();
		f.close();
	}

}
