package pw.mario.common.co;



import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named
@RequestScoped
@NoArgsConstructor
public class WelcomePageController {
	@Getter @Setter private String message;
	
	@PostConstruct
	private void init() {
		message = "Welcome home";
	}
}
