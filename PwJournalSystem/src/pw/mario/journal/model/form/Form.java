package pw.mario.journal.model.form;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.journal.model.AuditTable;

@Data
@EqualsAndHashCode(of="formId", callSuper=false)
@ToString(exclude="sections")
@NoArgsConstructor
@Entity
@Table(name="FORMS", indexes={
		@Index(columnList="PATTERN")
})
public class Form extends AuditTable implements Modifiable {
	@Id
	@Column(name="FORM_ID")
	@SequenceGenerator(name = "formIdSeq", sequenceName = "FORMS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="formIdSeq", strategy=GenerationType.SEQUENCE)
	private Long formId;
	
	@Column(name="NAME", length=120)
	private String name;
	
	@Column(name="PATTERN", nullable=false)
	private Boolean pattern;
	
	@OrderBy("order")
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="form")
	private List<Section> sections;
	
	@Column(name="LONG_ATTR1", length=4000)
	private String longAttr1;
	
	@Column(name="LONG_ATTR2", length=4000)
	private String longAttr2;

	@Override
	public void addChild() {
		if (sections == null)
			sections = new ArrayList<>();
		sections.add(new Section(this));
		
	}

	@Override
	public void delete() {
		if (sections != null)
			sections.clear();
	}
}
