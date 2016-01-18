package pw.mario.common.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pw.mario.journal.model.User;
import pw.mario.journal.service.UserService;

@Named("userLoginConverter")
@ApplicationScoped
public class UserConverter implements Converter {
	@Inject UserService service;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getUserByLogin(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		User u = (User)value;
		return u.getLogin();
	}

}
