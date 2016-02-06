package pw.mario.common.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Strings;

import pw.mario.journal.dao.UserDAO;
import pw.mario.journal.model.ext.IdTable;

@Named
@ApplicationScoped
public class UserOneMenuConverter implements Converter {
	@Inject private UserDAO user;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (!Strings.isNullOrEmpty(value) && NumberUtils.isNumber(value))
			return user.getUser(Long.parseLong(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IdTable)
			return ((IdTable)value).getId().toString();
		return null;
	}
	
}
