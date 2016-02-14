package pw.mario.faces.conf;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitHint;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.common.exception.LockException;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.model.form.SectionType;
import pw.mario.journal.service.form.ModalFormService;
import pw.mario.journal.view.form.ArticleFormBuilder;

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
		List<FacesMessage> errors = root.valid();
		
		if (errors != null && errors.size() > 0) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			errors.forEach(fm -> ctx.addMessage(null, fm));
			return;
		}
		
		try {
			root = formService.saveForm(root);
			Messages.addMessage("Zapisano formularz");
		} catch (LockException e) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Zmienił sie stan elementu");
		}
	}
	
	public void cancel() {
		init();
	}
	
	public List<SectionType> getSectionTypes() {
		return SectionType.list;
	}
	
	public void addChildrenTest() {
		Set<VisitHint> hints = new HashSet<>();
		hints.add(VisitHint.SKIP_UNRENDERED);
		hints.add(VisitHint.SKIP_ITERATION);
		FacesContext.getCurrentInstance().getViewRoot().visitTree(
			VisitContext.createVisitContext(FacesContext.getCurrentInstance(), null, hints),
			(ctx, comp) -> {
				if (comp.getId().equals("testTEST")) {
					ArticleFormBuilder bd = new ArticleFormBuilder(root, "reviewConfigCO.root");
					comp.getChildren().add(bd.build(FacesContext.getCurrentInstance()));
					return VisitResult.COMPLETE;
				}
				return VisitResult.ACCEPT;
			}
			
		);
	}
}
