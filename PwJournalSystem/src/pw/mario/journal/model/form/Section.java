package pw.mario.journal.model.form;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="sectionId")
@ToString(exclude={"elements", "form"})
@NoArgsConstructor
@Entity
@Table(name="SECTIONS")
public class Section implements Modifiable {
	@Id
	@Column(name="SECTION_ID")
	@SequenceGenerator(name = "sectionIdSeq", sequenceName = "SECTIONS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="sectionIdSeq", strategy=GenerationType.SEQUENCE)
	private Long sectionId;
	
	@Column(name="TITLE", length=120)
	private String title;
	
	@Column(name="DESCRIPTION", length=400)
	private String description;
	
	@Column(name="ORDER_NUM")
	private Integer order;
	
	@Column(name="SECTION_TYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	private SectionType sectionType;
	
	
	@OrderBy("order")
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="section")
	private List<Element> elements;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="FORM_ID", referencedColumnName="FORM_ID", nullable=false)
	private Form form;

	@Override
	public void addChild() {
		if (elements == null)
			elements = new ArrayList<>();
		elements.add(new Element(this));
	}

	@Override
	public void delete() {
		form.getSections().remove(this);
	}
	
	public Section(Form f) {
		form = f;
	}

	@Override
	public void order() {
		int i = 0;
		if (elements != null)
			for (Element e: elements) {
				e.setOrder(i++);
			}
	}
}
