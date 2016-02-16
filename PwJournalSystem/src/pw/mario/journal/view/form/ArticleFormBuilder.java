	package pw.mario.journal.view.form;

import static pw.mario.journal.view.FormUtils.createLabel;
import static pw.mario.journal.view.FormUtils.createValueExpression;
import static pw.mario.journal.view.FormUtils.createStrValueExpression;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.lang.model.element.Element;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;

import pw.mario.journal.model.form.Form;
import pw.mario.journal.model.form.Section;

public class ArticleFormBuilder extends BaseBuilder<Form>{
	private static final String ID_AUTHOR_TEXT_AREA = "itaForAuthor";
	private static final String ID_EDITOR_TEXT_AREA = "itaForEditor";
	private static final String COUNTER_TEMPLATE = "Pozostało {0} znaków";
	private static final int TEXT_AREA_COLS = 100;
	
	public ArticleFormBuilder(Form element, String path, boolean editable) {
		super(element, path, editable);
	}

	@Override
	public UIComponent build(FacesContext ctx) {
		Panel panel = new Panel();
		panel.setHeader(element.getName());
		setRoot(panel);
		put(buildTextAreas());

		int idx = 0;
		for(Section s: element.getSections()) {
			put(new ArticleFormSectionBuilder(s, path + ".sections.get(" + idx + ")" , editable).build(ctx));
			idx++;
		}

		return panel;
	}
	
	private UIComponent buildTextAreas() {
		PanelGrid grid = new PanelGrid();
		grid.setColumns(3);
		
		String itaAuthId= ID_AUTHOR_TEXT_AREA + element.getFormId();
		InputTextarea itaAuth = new InputTextarea();
		itaAuth.setId(itaAuthId);
		itaAuth.setCols(TEXT_AREA_COLS);
		itaAuth.setAutoResize(true);
		itaAuth.setAddLine(true);
		itaAuth.setCounterTemplate(COUNTER_TEMPLATE);
		itaAuth.setCounter(itaAuthId + "counter");
		itaAuth.setMaxlength(4000);
		itaAuth.setValueExpression("value", createValueExpression(path + ".longAttr1", String.class));
		itaAuth.setReadonly(!editable);
		
		put(grid, createLabel("Dla autora", itaAuthId));
		put(grid, itaAuth);
		put(grid, createLabel(itaAuthId + "counter", null, null));
		
		String itaEditId= ID_EDITOR_TEXT_AREA + element.getFormId();
		InputTextarea itaEditor = new InputTextarea();
		itaEditor.setId(itaEditId);
		itaEditor.setCols(TEXT_AREA_COLS);
		itaEditor.setAutoResize(true);
		itaEditor.setAddLine(true);
		itaEditor.setCounterTemplate(COUNTER_TEMPLATE);
		itaEditor.setCounter(itaEditId + "counter");
		itaEditor.setMaxlength(4000);
		itaEditor.setValueExpression("value", createValueExpression(path + ".longAttr2", String.class));
		itaEditor.setReadonly(!editable);
		
		put(grid, createLabel("Dla edytora", itaEditId));
		put(grid, itaEditor);
		put(grid, createLabel(itaEditId + "counter", null, null));
		
		return grid;
	}
	
	
}
