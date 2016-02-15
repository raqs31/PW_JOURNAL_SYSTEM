package pw.mario.journal.view.form;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

import org.primefaces.component.separator.UISeparator;
import org.primefaces.component.tabview.Tab;

import pw.mario.journal.model.form.Section;
import static pw.mario.journal.view.FormUtils.*;

public class ArticleFormSectionBuilder extends BaseBuilder<Section> {

	public ArticleFormSectionBuilder(Section element, String path) {
		super(element, path);
		Tab tab = new Tab();
		tab.setTitle(element.getTitle());
		
		setRoot(tab);
		
	}

	@Override
	public UIComponent build(FacesContext ctx) {
		UIOutput description = new UIOutput();
		description.setValue(element.getDescription());
		
		put(description);
		put(new UISeparator());
		put(element.getSectionType().builder(element, path).build(ctx));
		
		return getRoot();
	}

}
