package pw.mario.journal.model.dictionaries;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, name="DICTIONARY_NAME")
@Embeddable
public class DictionaryId implements Serializable {
	private static final long serialVersionUID = 9044085564285601027L;
	@Column(name="CODE", length=20, nullable=false)
	private String code;
	@Column(name="DICTIONARY_NAME", length=50, nullable=false)
	private String dictionaryName;

	public DictionaryId(final String code, final String dictionaryName) {
		this.code = code;
		this.dictionaryName = dictionaryName;
	}
}