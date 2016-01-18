package pw.mario.faces.articles.co;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	@Inject NewArticleService articleService;
	@Inject LoginService ctx;
	@Getter @Setter private Article article;
	@Getter @Setter private DualListModel<Tag> articleTags;
	@Getter @Setter private DualListModel<User> articleAuthors;
	@Getter @Setter private UploadedFile articleFile;
	@Getter @Setter private String articleFileName;
	private List<Tag> selectedTags;
	private List<User> selectedUser;

	@PostConstruct
	private void init() {
		article = articleService.initArticle();
		selectedTags = new LinkedList<>();
		selectedUser = new LinkedList<>();
		articleTags = new DualListModel<>(articleService.getTags(), selectedTags);
		articleAuthors = new DualListModel<>(articleService.getUsersFromDepartment(ctx.getCurrentUser().getDept()), selectedUser);
	}
	
	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}
	
	public void handleFileUploadListener(FileUploadEvent e) {
		articleFile = e.getFile();
		articleFileName = articleFile.getFileName();
			Messages.addMessage("Przesłano artykuł " + articleFile.getFileName());
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
