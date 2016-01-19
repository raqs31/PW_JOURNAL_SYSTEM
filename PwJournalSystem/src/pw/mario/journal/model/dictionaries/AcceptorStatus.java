package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(AcceptorStatus.DICTIONARY_NAME)
public class AcceptorStatus  extends Dictionary{
	public static final String DICTIONARY_NAME = "ACCEPTOR_STATUSES";
}
