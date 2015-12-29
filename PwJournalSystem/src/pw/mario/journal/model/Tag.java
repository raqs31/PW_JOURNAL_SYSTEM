package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import pw.mario.journal.model.ext.IdTable;

@Data
@Entity
@Table(name="TAGS", indexes={@Index(columnList="NAME", unique=true)})
public class Tag implements IdTable {
	@Id
	@Column(name="TAG_ID")
	@SequenceGenerator(name="tagIdSeq", sequenceName="TAGS_SEQ", initialValue=1)
	@GeneratedValue(generator="tagIdSeq", strategy=GenerationType.SEQUENCE)
	private long tagId;
	
	@Column(name="NAME", unique=true, length=20)
	private String name;
	
	@Column(name="DESCRIPTION", length=240)
	private String description;

	@Override
	public Object getId() {
		return tagId;
	}
}
