package pw.mario.journal.view.form.section;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.util.GridLayoutUtils;

import pw.mario.journal.model.form.Element;
import pw.mario.journal.model.form.Section;
import pw.mario.journal.view.FormUtils;
import pw.mario.journal.view.form.BaseBuilder;
import static pw.mario.journal.view.FormUtils.*;

public class TextSection extends BaseBuilder<Section> {
	private static final String TEXT_AREA_PREFIX = "_ta";
	
	private PanelGrid grid;
	public TextSection(Section element, String path) {
		super(element, path);
		
		grid = new PanelGrid();
		grid.setColumns(2);
		
		setRoot(grid);
	}

	@Override
	public UIComponent build(FacesContext ctx) {
		int idx = 0;
		
		for (Element el: element.getElements()){
			String elId = TEXT_AREA_PREFIX + el.getElemId().toString();
			put(createLabel(el.getDescription(), elId));
			InputTextarea textArea = new InputTextarea();
			textArea.setId(elId);
			textArea.setAutoResize(true);
			textArea.setAddLine(true);
			textArea.setMaxlength(4000);
			textArea.setCols(70);
			textArea.setRows(5);
			textArea.setValueExpression("value", createValueExpression(path + ".elements.get(" + idx + ").value", String.class));
			put(textArea);
			idx++;
		}
		
		return grid;
	}

}
