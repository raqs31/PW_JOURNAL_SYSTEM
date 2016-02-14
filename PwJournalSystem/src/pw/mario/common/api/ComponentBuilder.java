package pw.mario.common.api;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public interface ComponentBuilder{
	UIComponent build(FacesContext ctx);
}
