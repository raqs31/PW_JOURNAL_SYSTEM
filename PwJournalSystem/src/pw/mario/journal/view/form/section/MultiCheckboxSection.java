package pw.mario.journal.view.form.section;

import static pw.mario.journal.view.FormUtils.createStrValueExpression;
import static pw.mario.journal.view.FormUtils.createValueExpression;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;

import pw.mario.journal.model.form.Section;
import pw.mario.journal.view.form.BaseBuilder;

public class MultiCheckboxSection extends BaseBuilder<Section> {
	public static final String SELECTED_VALUE = "Y";
	
	public MultiCheckboxSection(Section element, String path) {
		super(element, path);
		SelectManyCheckbox selectMany = new SelectManyCheckbox();
		selectMany.setLayout("grid");
		selectMany.setColumns(1);
		selectMany.setValueExpression("value", createValueExpression(path+".selectedElementsIds", List.class));
		setRoot(selectMany);
		
	}

	@Override
	public UIComponent build(FacesContext ctx) {
		UISelectItems items = new UISelectItems();
		items.setValueExpression("value", createValueExpression(path + ".elements", List.class));
		items.setValueExpression("var", createStrValueExpression("el", String.class));
		items.setValueExpression("itemLabel", createValueExpression("el.description", String.class));
		items.setValueExpression("itemValue", createValueExpression("el.elemId", Long.class));
		
		put(items);
		return getRoot();
	}

}
