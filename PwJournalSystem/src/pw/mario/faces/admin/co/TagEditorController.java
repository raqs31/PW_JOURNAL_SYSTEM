package pw.mario.faces.admin.co;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.journal.model.Tag;
import pw.mario.journal.service.TagService;

@NoArgsConstructor
@ManagedBean(name="tagEditorController")
@ViewScoped
public class TagEditorController {
	@Inject
	private TagService tagService;

	@Getter
	@Setter
	private List<Tag> tags;
	@Getter
	@Setter
	private Tag selectedTag;
	@Getter
	@Setter
	private Tag editTag;
	@Getter
	@Setter
	private Tag newTag;

	@PostConstruct
	private void init() {
		tags = tagService.getTags();
		newTag = new Tag();
	}
	public void addTag() {
		Tag t = tagService.addTag(newTag);
		tags.add(t);
		newTag = new Tag();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano tag", t.getName()));
	}
	
	public void removeTag() {
		
	}
	
	public void updateTag() {
		tagService.updateTag(editTag);
		
	}
}
