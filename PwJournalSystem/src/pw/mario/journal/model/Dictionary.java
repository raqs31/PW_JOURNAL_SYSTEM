package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.ext.IdTable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DICTIONARIES", schema="MARIO", indexes={
		@Index(columnList="CODE,DICTIONARY_NAME", unique=true)
})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, name="DICTIONARY_NAME")
public class Dictionary implements IdTable{
	@Id
	@SequenceGenerator(name="dictSeq", sequenceName="DICTIONARY_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="dictSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="DICT_ID")
	private Long id;
	
	@Column(name="CODE", length=20, nullable=false, updatable=false, insertable=false)
	private String code;
	@Column(name="DICTIONARY_NAME", length=50, nullable=false, updatable=false, insertable=false)
	private String dictionaryName;
	
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
	private Long nAttr1;
	
	@Column(name="NUM_ATTR2")
	private Long nAttr2;
}
