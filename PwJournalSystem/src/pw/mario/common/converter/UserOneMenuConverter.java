package pw.mario.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.selectonemenu.SelectOneMenu;

import pw.mario.journal.model.User;

@FacesConverter(value="userOneMenuConverter", forClass=User.class)
public class UserOneMenuConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SelectOneMenu selectOneMenu = (SelectOneMenu) component;
		if (value != null) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((User)value).getUserId().toString(); 
		}
		return null;
	}
	
}
