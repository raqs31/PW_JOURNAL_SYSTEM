package pw.mario.journal.model.dictionaries;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;

import javax.faces.application.FacesMessage.Severity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;


@Entity
@NoArgsConstructor
@DiscriminatorValue(ValidationRule.DICTIONARY_NAME)
public class ValidationRule extends Dictionary {
	public static final String DICTIONARY_NAME = "VALIDATION_RULE";
	
	public Severity getSeverity() {
		if (getNAttr1() == null)
			return SEVERITY_ERROR;
		Severity result = SEVERITY_ERROR;
		
		switch (getNAttr1().intValue()) {
			case 0:
				result = SEVERITY_INFO;
				break;
			case 1:
				result = SEVERITY_WARN;
				break;
			case 2:
				result = SEVERITY_ERROR;
				break;
			case 3:
				result = SEVERITY_FATAL;
				break;
			
		}
		
		return result;
	}
}
