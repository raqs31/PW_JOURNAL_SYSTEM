package pw.mario.common.co;



import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named
@RequestScoped
@NoArgsConstructor
public class WelcomePageController {
	@Getter @Setter private String message;
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		message = "Welcome home";
		em.createQuery("select a from Article a").getResultList();
	}
}
