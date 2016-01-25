package pw.mario.faces.articles.co;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.util.JSFUtil;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.ArticleManagement;
import pw.mario.journal.qualifiers.enums.ArticleManager;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.ArticleService;

@Log4j
@Named
@ViewScoped
public class ArticleDetailsController implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_ARTICLE_ID = "articleId";

	@Getter @Setter private Article article;
	@Getter @Setter private List<ButtonAction> actions;
	
	@Inject @ArticleManagement(ArticleManager.AUTHOR) private ArticleService articleService;
	@Inject transient private LoginService ctx;
	@PostConstruct
	private void init() {
		if (article == null) {
			Article flashArticle = (Article) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(PARAM_ARTICLE_ID);
			if (flashArticle == null)
				JSFUtil.redirect("articles.xhtml?faces-redirect=true");
			else {
				article = articleService.getArticle(flashArticle.getArticleId(), null);
				actions = new LinkedList<>();
				articleService.getActions(article, ctx.getCurrentUser()).forEach(b -> actions.add(b));
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
}
