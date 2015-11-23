package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ACCEPTOR_STATUS")
public class AcceptorStatus  extends Dictionary{
	public static final String DICTIONARY_NAME = "ARTICLE_STATUS";
	
	public AcceptorStatus(String code) {
		if (getId() == null)
			setId(new DictionaryId(code, DICTIONARY_NAME));
		else {
			getId().setCode(code);
			getId().setDictionaryName(DICTIONARY_NAME);
		}
	}
}
