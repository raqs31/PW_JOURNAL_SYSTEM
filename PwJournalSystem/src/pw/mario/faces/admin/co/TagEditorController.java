package pw.mario.faces.admin.co;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.journal.model.Tag;
import pw.mario.journal.service.TagService;

@NoArgsConstructor
@Named
@ViewScoped
public class TagEditorController implements Serializable {
	private static final long serialVersionUID = 1L;

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
		tags.remove(selectedTag);
		tagService.removeTag(selectedTag);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto", selectedTag.getName()));
	}
	
	public void updateTag() {
		tagService.updateTag(selectedTag);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano etykietę", selectedTag.getName()));
	}
}
