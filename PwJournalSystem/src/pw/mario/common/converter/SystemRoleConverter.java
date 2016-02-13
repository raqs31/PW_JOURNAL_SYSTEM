package pw.mario.common.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pw.mario.journal.dao.SystemRolesDAO;
import pw.mario.journal.model.common.SystemRole;

@Named("sysRoleConverter")
@ApplicationScoped
public class SystemRoleConverter implements Converter {
	@Inject private SystemRolesDAO dao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return dao.getSystemRole(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((SystemRole)value).getRoleName();
	}

}
