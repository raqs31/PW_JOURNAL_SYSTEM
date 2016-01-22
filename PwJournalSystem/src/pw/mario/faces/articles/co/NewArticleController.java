package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import antlr.debug.Event;
import lombok.Getter;
import lombok.Setter;
import pw.mario.common.api.UserList;
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
	@Getter @Setter private UploadedFile articleFile;
	@Getter @Setter private String articleFileName;
	
	
	@PostConstruct
	private void init() {
		article = articleService.initArticle();
		selectedTags = new LinkedList<>();
		selectedUser = new LinkedList<>();
		articleTags = new DualListModel<>(articleService.getTags(), selectedTags);
		articleAuthors = new DualListModel<>(articleService.getUsersFromDepartment(ctx.getCurrentUser().getDept()), selectedUser);
//		conversation.begin();
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
			Messages.addMessage("Przesłano artykuł " + articleFile.getFileName());
	}
	
	public void save() {
		System.out.println(article);
		System.out.println(articleFile);
		System.out.println(articleTags.getTarget());
		System.out.println(articleAuthors.getTarget());
//		conversation.end();
	}
	
	private class UsersFromDepartment implements UserList {
		@Getter @Setter List<User> users;
		@Getter @Setter List<User> selectedUsers;

		@Override
		public void onRowSelect(SelectEvent e) {}

		@Override
		public void onRowUnselect(UnselectEvent e) {}

		@Override
		public void onUserEdit(RowEditEvent e) {}

		@Override
		public boolean getLoginRendered() {
			return false;
		}

		@Override
		public boolean getEmailRendered() {
			return false;
		}
	}
}
