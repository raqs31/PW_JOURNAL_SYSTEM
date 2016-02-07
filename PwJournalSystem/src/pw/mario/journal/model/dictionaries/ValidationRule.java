package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(ValidationRule.DICTIONARY_NAME)
public class ValidationRule extends Dictionary {
	public static final String DICTIONARY_NAME = "VALIDATION_RULE";

}
