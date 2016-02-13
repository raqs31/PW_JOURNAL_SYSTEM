package pw.mario.faces.conf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.journal.model.form.Form;

@Named
@ViewScoped
@NoArgsConstructor
public class ReviewConfigCO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Form root;
	
	@PostConstruct
	private void init() {
		root = new Form();
		root.setPattern(true);
		root.setName("Wzór formularza recenzji");
		root.setLongAttr1("Przykładowy tekst do edyotra");
		root.setLongAttr2("Przykładowy tekst do autora");
	}
}
