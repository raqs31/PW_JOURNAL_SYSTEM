package pw.mario.faces.admin.validator;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import com.google.common.base.Strings;

import pw.mario.journal.dao.common.DepartmentDAO;

@Named
@RequestScoped
public class DepartmentValidator implements Serializable, Validator{
	private static final long serialVersionUID = 1L;
	@Inject private DepartmentDAO dao;
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String toVal = (String) value;
		if (Strings.isNullOrEmpty(toVal)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Należy podać kod departamentu", null));
		} else {
			try {
				if (dao.getDepartment(toVal) != null)
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departament o kodzie " + toVal + " już istnieje", null));
			} catch (NoResultException e) {
					; // nazwa jest wolna
			}
		}
	}

}
