package pw.mario.faces.common.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.google.common.base.Strings;

import pw.mario.journal.dao.TagDAO;
import pw.mario.journal.dao.UserDAO;

@ManagedBean(name="tagValidator", eager=true)
public class TagValidator implements Validator {
	@Inject TagDAO tagDao;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String toVal = (String) value;
		if (Strings.isNullOrEmpty(toVal)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Należy podać nazwę", null));
		} else {
			try {
				if (tagDao.getTag(toVal) != null)
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tag o takiej nazwie istnieje", null));
			} catch (NoResultException e) {
					; // nazwa jest wolna
			}
		}
	}

}
