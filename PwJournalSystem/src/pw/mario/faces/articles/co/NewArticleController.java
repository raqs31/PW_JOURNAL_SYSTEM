package pw.mario.faces.articles.co;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.Messages;
import pw.mario.common.util.file.FileHandler;
import pw.mario.journal.model.article.Article;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.NewArticleService;

@Log4j
@Named
@ViewScoped
public class NewArticleController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String lastStep = "article";
	private List<Tag> selectedTags;
	private List<User> selectedUser;
	private FileHandler fileHandler;
	
	@Inject private NewArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private Article article;
	@Getter @Setter private DualListModel<Tag> articleTags;
	@Getter @Setter private DualListModel<User> articleAuthors;
	@Getter @Setter private String articleFileName;
	
	
	@PostConstruct
	private void init() {
		article = articleService.initArticle();
		selectedTags = new LinkedList<>();
		selectedUser = new LinkedList<>();
		articleTags = new DualListModel<>(articleService.getTags(), selectedTags);
		articleAuthors = new DualListModel<>(articleService.getUsers(ctx.getCurrentUser().getDept()), selectedUser);
	}
	
	public String onFlowProcess(FlowEvent event) {
		String nextStep = event.getNewStep(); 
		if (lastStep.equals(nextStep)) {
			Messages.addMessage(FacesMessage.SEVERITY_INFO, "Wprowadzenie pliku artykułu jest opcjonalne", "Plik można zmienić po utworzeniu artykułu");
		}
		return nextStep;
	}
	
	public void handleFileUploadListener(FileUploadEvent e) {
		UploadedFile uploaded = e.getFile();
		articleFileName = uploaded.getFileName();
		
		try {
			File tmp = File.createTempFile(ctx.getCurrentUser().getUserId() + "_" + articleFileName , "tmp");
			uploaded.write(tmp.getAbsolutePath());
			fileHandler = new FileHandler(tmp, articleFileName);
			Messages.addMessage("Przesłano artykuł " + articleFileName);
		} catch (Exception ex) {
			log.error("Error while create tmpFile", ex);
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, "Nie udało się przesłać pliku " + articleFileName, ex.getMessage());
		} finally {
			try {
				uploaded.getInputstream().close();
			} catch (IOException e1) {
				log.error("Cannot close uploaded file inputStream", e1);
				e1.printStackTrace();
			}
		}
	}
	
	public String save() {
		try {
			article.setAuthors(new HashSet<>(articleAuthors.getTarget()));
			article.getAuthors().add(ctx.getCurrentUser());
			
			article.setTagList(new HashSet<>(articleTags.getTarget()));
			articleService.createArticle(article, fileHandler);
			
			Messages.keepMessages();
			Messages.addMessage("Utworzono artykuł");

			FacesContext.getCurrentInstance()
				.getExternalContext()
				.getFlash()
				.put(ArticleDetailsController.PARAM_ARTICLE_ID, article);
			return "articleDetails?faces-redirect=true";	
		} catch (PerformActionException ex) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, "Coś nie pykło", ex.getMessage());
		}
		return null;
	}
}
