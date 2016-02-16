package pw.mario.faces.articles.co;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigestSpi;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitHint;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.JSFUtil;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.article.ArticleAcceptor;
import pw.mario.journal.model.article.ArticleVersion;
import pw.mario.journal.model.common.User;
import pw.mario.journal.model.form.Form;
import pw.mario.journal.service.article.ArticleOperationService;
import pw.mario.journal.service.common.LoginService;
import pw.mario.journal.service.form.ModalFormService;
import pw.mario.journal.view.form.ArticleFormBuilder;

@Log4j
@Named
@ViewScoped
public class ArticleDetailsController implements Serializable, Refreshable {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_ARTICLE_ID = "articleId";

	@Getter @Setter private Article article;
	@Getter @Setter private Collection<ButtonAction> actions;
	@Getter @Setter private Map<Long, Form> visibleForms;
	
	@Inject private ArticleOperationService articleService;
	@Inject transient private LoginService ctx;
	
	@PostConstruct
	private void init() {
		if (article == null) {
			Article flashArticle = (Article) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(PARAM_ARTICLE_ID);
			if (flashArticle == null)
				JSFUtil.redirect("articles.xhtml?faces-redirect=true");
			else {
				article = flashArticle;
				refresh();
			}
		}
		visibleForms = new HashMap<>();
	}
	
	public StreamedContent onDownload(ArticleVersion version) {
		ArticleAcceptor acceptor = version.getAcceptor();
		User current = ctx.getCurrentUser();
		if (acceptor != null && !acceptor.getApply() && current.compareTo(acceptor.getAcceptor()) != 0 && current.compareTo(article.getManagement()) != 0) {
			Messages.addMessage(FacesMessage.SEVERITY_WARN, "Artykuł nie jest w odpowiednim stanie", "Recenzja nie może zostać pobrana");
			return null;
		}
		
		
		File file = new File(version.getAttachement());
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
			StreamedContent stream = new DefaultStreamedContent(input, ectx.getMimeType(file.getName()), file.getName());
			return stream;
		} catch (FileNotFoundException e) {
			log.fatal("File not found", e);
			Messages.addMessage(FacesMessage.SEVERITY_FATAL, "Nie odnaleziono pliku", file.getName());
		}
		return null;
	}

	@Override
	public void refresh() {
		article = articleService.getArticle(article.getArticleId(), null);
		actions = articleService.getActions(article, ctx.getCurrentUser(), this);
	}
	
	public void showAcceptorReview(ArticleAcceptor acceptor) {
		Form form = acceptor.getAcceptorForm();
		
		if (form == null) {
			Messages.addAccessDeniedMessage("Recenzent nie ma utworzonego formularza recenzji");
			return;
		} else if (visibleForms.containsKey(form.getFormId()))
			return;
		
		visibleForms.put(form.getFormId(), form);
		
		Set<VisitHint> hints = new HashSet<>();
		hints.add(VisitHint.SKIP_UNRENDERED);
		hints.add(VisitHint.SKIP_ITERATION);
		FacesContext.getCurrentInstance().getViewRoot().visitTree(
			VisitContext.createVisitContext(FacesContext.getCurrentInstance(), null, hints),
			(ctx, comp) -> {
					if (comp instanceof HtmlForm && "articleAcceptorsReview".equals(comp.getId())) {
						ArticleFormBuilder bd = new ArticleFormBuilder(acceptor.getAcceptorForm(), 
																			"articleDetailsController.visibleForms.get(" + form.getFormId() + ")" ,
																			acceptor.isEditable(this.ctx.getCurrentUser()),
																			article.isAuthor(this.ctx.getCurrentUser()));
						comp.getChildren().add(bd.build(FacesContext.getCurrentInstance()));
						return VisitResult.COMPLETE;
					}
					return VisitResult.ACCEPT;
				}
		);
	}
	
	public void saveForms() {
		try {
			articleService.saveAcceptorsForms(article);
			Messages.addMessage("Zapisano formularze");
		} catch (PerformActionException e) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
	}
}
