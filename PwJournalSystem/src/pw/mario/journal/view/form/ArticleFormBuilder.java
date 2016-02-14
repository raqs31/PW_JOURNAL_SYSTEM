package pw.mario.journal.view.form;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;

import pw.mario.common.api.ComponentBuilder;
import pw.mario.journal.model.form.Form;

import static pw.mario.journal.view.FormUtils.*;

public class ArticleFormBuilder implements ComponentBuilder{
	private static final String ID_AUTHOR_TEXT_AREA = "itaForAuthor";
	private static final String ID_EDITOR_TEXT_AREA = "itaForEditor";
	private static final String COUNTER_TEMPLATE = "Pozostało {0} znaków";
	private static final int TEXT_AREA_COLS = 100;
	
	private Form root;
	private String path;
	
	public ArticleFormBuilder(Form root, String path) {
		this.root = root;
		this.path = path;
	}

	@Override
	public UIComponent build(FacesContext ctx) {
		Panel panel = new Panel();
		panel.setHeader(root.getName());
		put(panel, buildTextAreas());

		return panel;
	}
	
	private UIComponent buildTextAreas() {
		PanelGrid grid = new PanelGrid();
		grid.setColumns(3);
		put(grid, createLabel("Dla autora", ID_AUTHOR_TEXT_AREA));
		
		InputTextarea itaAuth = new InputTextarea();
		itaAuth.setId(ID_AUTHOR_TEXT_AREA);
		itaAuth.setCols(TEXT_AREA_COLS);
		itaAuth.setAutoResize(true);
		itaAuth.setAddLine(true);
		itaAuth.setCounterTemplate(COUNTER_TEMPLATE);
		itaAuth.setCounter(ID_AUTHOR_TEXT_AREA + "counter");
		itaAuth.setMaxlength(4000);
		itaAuth.setValueExpression("value", creteValueExpression(path + ".longAttr1", String.class));
		
		put(grid, itaAuth);
		put(grid, createLabel(ID_AUTHOR_TEXT_AREA + "counter", null, null));
		
		return grid;
	}
	
	
}
