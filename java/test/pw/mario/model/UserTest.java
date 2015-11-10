package pw.mario.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pw.mario.journal.model.User;

public class UserTest {
	EntityManagerFactory f;
	EntityManager em;
	
	
	@Before
	public void setUp() {
		f = Persistence.createEntityManagerFactory("PW_JOURNAL_SYSTEM");
		em = f.createEntityManager();
		em.getTransaction().begin();
	}
	@Test
	public void testUser() {
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setName("UserXXX-" + i);
			u.setEmail("user_test@test."+i);
			u.setLogin("Mario" + i);
			u.setSecondName("Bross"+i);
			
			em.persist(u);
		}
	}
	@After
	public void tearDown() {
		em.getTransaction().commit();
		em.close();
		f.close();
	}

}
