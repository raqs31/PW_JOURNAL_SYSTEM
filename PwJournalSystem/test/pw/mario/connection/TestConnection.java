package pw.mario.connection;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jboss.security.auth.spi.Util;
import org.junit.Test;

import pw.mario.journal.model.User;

public class TestConnection {

	@SuppressWarnings("unchecked")
	@Test
	public void testEclipseLink() {
		System.out.println(Util.createPasswordHash("SHA-256", "BASE64", null, null,"test"));
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("PwJournalSystem");
		em = emf.createEntityManager();
		Query q = em.createQuery("Select u from User u");
		List<User> users = q.getResultList();
		System.out.println(Arrays.toString(users.toArray()));
		em.close();
		emf.close();
	}
	
}
