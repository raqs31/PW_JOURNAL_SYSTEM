package pw.mario.faces.articles.co;

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
import pw.mario.common.exception.PerformActionException;
import pw.mario.common.util.Messages;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Tag;
import pw.mario.journal.model.User;
import pw.mario.journal.service.LoginService;
import pw.mario.journal.service.article.NewArticleService;

@Named
@ViewScoped
public class NewArticleController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String lastStep = "article";
	private List<Tag> selectedTags;
	private List<User> selectedUser;

	@Inject private NewArticleService articleService;
	@Inject private LoginService ctx;
//	@Inject private Conversation conversation;
	
	@Getter @Setter private Article article;
	@Getter @Setter private DualListModel<Tag> articleTags;
	@Getter @Setter private DualListModel<User> articleAuthors;
	@Getter @Setter private String articleFileName;
	private UploadedFile articleFile;
	
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
		articleFile = e.getFile();
		articleFileName = articleFile.getFileName();
		Messages.addMessage("Przesłano artykuł " + articleFileName);
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
