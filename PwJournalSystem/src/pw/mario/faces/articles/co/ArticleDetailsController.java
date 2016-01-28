package pw.mario.faces.articles.co;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import pw.mario.common.util.JSFUtil;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleOperationService;

@Log4j
@Named
@ViewScoped
public class ArticleDetailsController implements Serializable, Refreshable {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_ARTICLE_ID = "articleId";

	@Getter @Setter private Article article;
	@Getter @Setter private Collection<ButtonAction> actions;
	@Getter @Setter private List<Rule> rules;
	
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
				
				rules = articleService.getAvailableSteps(article, ctx.getCurrentUser());
				rules.forEach(r -> log.debug(article.getId() + ": " + r.getDescription()));
			}
			
		}
	}
	
	public StreamedContent onDownload(String path) {
		File file = new File(path);
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
		actions = articleService.getActions(article, ctx.getCurrentUser());
	}
}
