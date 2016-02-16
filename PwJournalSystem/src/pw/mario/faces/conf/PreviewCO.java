package pw.mario.faces.conf;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitHint;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.service.form.ModalFormService;
import pw.mario.journal.view.form.ArticleFormBuilder;

@Named
@ViewScoped
public class PreviewCO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter @Setter private Form root;
	@Inject private ModalFormService formService;
	
	public void init() {
		if (root != null)
			return;
		root = formService.getArticleFormPattern();
		Set<VisitHint> hints = new HashSet<>();
		hints.add(VisitHint.SKIP_UNRENDERED);
		hints.add(VisitHint.SKIP_ITERATION);
		FacesContext.getCurrentInstance().getViewRoot().visitTree(
			VisitContext.createVisitContext(FacesContext.getCurrentInstance(), null, hints),
			(ctx, comp) -> {
				if (comp.getId().equals("previewForm")) {
					ArticleFormBuilder bd = new ArticleFormBuilder(root, "previewCO.root");
					comp.getChildren().add(bd.build(FacesContext.getCurrentInstance()));
					return VisitResult.COMPLETE;
				}
				return VisitResult.ACCEPT;
			}
			
		);
	}
}
