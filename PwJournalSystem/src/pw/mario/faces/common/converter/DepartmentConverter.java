package pw.mario.faces.common.converter;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.google.common.base.Strings;

import pw.mario.journal.dao.DepartmentDAO;
import pw.mario.journal.model.Department;

@ManagedBean(eager=true, name="deptConverter")
@Singleton
public class DepartmentConverter implements Converter {
	@Inject DepartmentDAO deptDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (!Strings.isNullOrEmpty(value)) {
			Department d = deptDao.getDepartment(value);
			System.out.println(d);
			return d;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Department)
			return ((Department)value).getDeptCode();
		return null;
	}

}
