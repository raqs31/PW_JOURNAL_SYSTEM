package pw.mario.common.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import com.google.common.base.Strings;

import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;

@Named("deptConverter")
@ApplicationScoped
public class DepartmentConverter implements Converter {
	@Inject
	DepartmentDAO deptDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (!Strings.isNullOrEmpty(value)) {
			try {
				Department d = deptDao.getDepartment(value);
				return d;
			} catch (NoResultException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Department)
			return ((Department) value).getDeptCode();
		return null;
	}

}
