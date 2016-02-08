package pw.mario.journal.model.dictionaries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import pw.mario.journal.model.Dictionary;

@Entity
@NoArgsConstructor
@DiscriminatorValue(AcceptorState.DICTIONARY_NAME)
public class AcceptorState extends Dictionary{
	public final static String DICTIONARY_NAME = "ACCEPTOR_STATES";
}
