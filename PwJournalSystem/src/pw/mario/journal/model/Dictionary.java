package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.dictionaries.DictionaryId;
import pw.mario.journal.model.ext.IdTable;

@Data
@NoArgsConstructor
@Entity
@Table(name="DICTIONARIES", schema="MARIO")
public class Dictionary implements IdTable{
	
	@EmbeddedId
	private DictionaryId id;
	
	@Column(name="DESCRIPTION")
	private String desciption;
	
	@Column(name="PARENT_CODE")
	private String parentCode;
	
	@Column(name="ATTRIBUTE1", length=120)
	private String attr1;
	
	@Column(name="ATTRIBUTE2", length=120)
	private String attr2;
	
	@Column(name="ATTRIBUTE3", length=120)
	private String attr3;
	
	@Column(name="ATTRIBUTE4", length=120)
	private String attr4;
	
	@Column(name="NUM_ATTR1")
	private long nAttr1;
	
	@Column(name="NUM_ATTR2")
	private long nAttr2;
	
	public Dictionary(String code) {
		if (id == null)
			id = new DictionaryId();
		id.setCode(code);
	}
	
	public Dictionary(String code, String dictionaryName) {
			id = new DictionaryId(code, dictionaryName);
	}
}
