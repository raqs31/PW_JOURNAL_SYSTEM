package pw.mario.faces.conf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.service.form.ModalFormService;

@Named
@ViewScoped
@NoArgsConstructor
public class ReviewConfigCO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private ModalFormService formService;
	
	@Getter @Setter private Form root;
	
	@PostConstruct
	private void init() {
		root = formService.getArticleFormPattern();
		
		if (root == null)
			root = initForm();
	}
	
	
	private Form initForm() {
		Form form = new Form();
		form.setPattern(true);
		form.setPatternCode(Form.PatternCode.ARTICLE_ACCEPTOR);
		return form;
	}
	
	public void saveForm() {
		formService.saveForm(root);
	}
	
	public void cancel() {
		init();
	}
}
