package pw.mario.faces.admin.validator;

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

import pw.mario.journal.dao.TagDAO;

@Named
@RequestScoped
public class TagValidator implements Validator {
	@Inject TagDAO tagDao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
