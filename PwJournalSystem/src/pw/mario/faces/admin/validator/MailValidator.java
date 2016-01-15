package pw.mario.faces.admin.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.google.common.base.Strings;

import pw.mario.journal.dao.UserDAO;

@ManagedBean(name="mailValidator", eager=true)
public class MailValidator implements Validator {
	@Inject UserDAO userDao;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String toVal = (String) value;
		if (Strings.isNullOrEmpty(toVal)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Należy podać email", null));
		} else {
			try {
				if (userDao.getUserByEmail(toVal) != null)
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Taki mail jest już w systemie", null));
			} catch (NoResultException e) {
					; //Uzytkownik nie istnieje
			}
		}
	}

}