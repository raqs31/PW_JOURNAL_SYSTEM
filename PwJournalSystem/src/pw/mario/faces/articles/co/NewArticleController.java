package pw.mario.faces.articles.co;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.NewArticleService;
import pw.mario.journal.util.files.FileHandler;
import pw.mario.journal.util.files.FileUtils;

@Log4j
@Named
@ViewScoped
public class NewArticleController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String lastStep = "article";
	private List<Tag> selectedTags;
	private List<User> selectedUser;
	private FileHandler articleFile;
	
	@Inject private NewArticleService articleService;
	@Inject private LoginService ctx;
	
	@Getter @Setter private Article article;
	@Getter @Setter private DualListModel<Tag> articleTags;
	@Getter @Setter private DualListModel<User> articleAuthors;
	@Getter @Setter private String articleFileName = "test";
	
	
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
		try {
			articleFile = FileUtils.createTempFile(uploaded.getInputstream(), uploaded.getFileName());
			articleFileName = articleFile.getFileName();
			Messages.addMessage("Przesłano artykuł " + articleFileName);
		} catch (IOException ex) {
			log.error("Error while create tmpFile", ex);
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, "Nie udało się przesłać pliku " + articleFileName, ex.getMessage());
		} finally {
			try {
				uploaded.getInputstream().close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void save() {
		System.out.println(article);
		System.out.println(articleTags.getTarget());
		System.out.println(articleAuthors.getTarget());
		try {
			articleService.createArticle(article, articleFile);
		} catch (PerformActionException ex) {
			Messages.addMessage(FacesMessage.SEVERITY_ERROR, "Coś nie pykło", ex.getMessage());
		}
	}
}
