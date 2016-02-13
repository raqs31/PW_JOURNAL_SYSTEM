package pw.mario.journal.model.dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue(AcceptorStatus.DICTIONARY_NAME)
public class AcceptorStatus  extends Dictionary{
	public static final String DICTIONARY_NAME = "ACCEPTOR_STATUSES";
}
