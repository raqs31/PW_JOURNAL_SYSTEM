import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestConection {
	private static final Logger logger = Logger.getLogger(TestConection.class);
	
	@Test
	public void testConnection() {
		try {
			EntityManagerFactory f = Persistence.createEntityManagerFactory("PW_JOURNAL_SYSTEM");
			EntityManager em = f.createEntityManager();
			em.getTransaction().begin();

			
			em.close();
			f.close();
			logger.debug("DONE!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
