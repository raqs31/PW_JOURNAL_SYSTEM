package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(SystemParameter.DICTIONARY_NAME)
public class SystemParameter extends Dictionary {
	public static final String DICTIONARY_NAME = "SYSTEM_PARAM"; 
	
	public String getPropertyName() {
		return getAttr1();
	}
	
	public String getPropertyValue() {
		return getAttr2();
	}
}
