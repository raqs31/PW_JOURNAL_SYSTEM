package pw.mario.journal.model.dictionaries;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class DictionaryId implements Serializable {
	private static final long serialVersionUID = 9044085564285601027L;
	@Column(name="CODE")
	private String code;
	@Column(name="DICTIONARY_NAME")
	private String dictionaryName;

	public DictionaryId(final String code, final String dictionaryName) {
		this.code = code;
		this.dictionaryName = dictionaryName;
	}
}