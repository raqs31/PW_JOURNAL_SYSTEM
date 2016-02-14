package pw.mario.faces.admin.validator;

import java.util.regex.Pattern;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

import pw.mario.journal.dao.common.UserDAO;

@Named
@RequestScoped
public class MailValidator implements Validator {
	private static final Pattern mailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	@Inject UserDAO userDao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String toVal = (String) value;
		if (Strings.isNullOrEmpty(toVal)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Należy podać email", null));
		} else if (!mailPattern.matcher(toVal).matches()) { 
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail ma nieprawidłowy format", null));
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
