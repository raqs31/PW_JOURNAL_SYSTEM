package pw.mario.journal.view.form.section;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectoneradio.SelectOneRadio;

import pw.mario.journal.model.form.Section;
import static pw.mario.journal.view.FormUtils.*;

import java.util.List;

import pw.mario.journal.view.form.BaseBuilder;

public class SingleBoxSection extends BaseBuilder<Section> {
	public static final String SELECTED_VALUE = "Y";
	public SingleBoxSection(Section element, String path) {
		super(element, path);
		SelectOneRadio oneRadio = new SelectOneRadio();
		oneRadio.setLayout("grid");
		oneRadio.setColumns(1);
		oneRadio.setValueExpression("value", createValueExpression(path+".selectedElementId", String.class));
		setRoot(oneRadio);
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
