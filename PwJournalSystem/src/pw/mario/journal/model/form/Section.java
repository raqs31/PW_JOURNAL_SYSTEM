package pw.mario.journal.model.form;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
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
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Strings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.common.api.Copyable;
import pw.mario.common.api.Modifiable;

@Data
@EqualsAndHashCode(of="sectionId")
@ToString(exclude={"elements", "form"})
@NoArgsConstructor
@Entity
@Table(name="SECTIONS")
public class Section implements Modifiable, Copyable<Section> {
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
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="section", orphanRemoval=true)
	private List<Element> elements;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="FORM_ID", referencedColumnName="FORM_ID", nullable=false)
	private Form form;

	@Transient
	private String selectedElementId;
	
	@Transient
	private List<String> selectedElementsIds;
	
	@Override
	public void addChild() {
		if (elements == null)
			elements = new ArrayList<>();
		Element e = new Element(this);
		e.setOrder(elements.size());
		elements.add(e);
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

	@Override
	public List<FacesMessage> valid() {
		List<FacesMessage> errors = new LinkedList<>();
		if (Strings.isNullOrEmpty(title))
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie wprowadzono tytułu", forDetail()));
		if (sectionType == null)
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie wybrano typu sekcji", forDetail()));
		if (elements != null)
			elements.forEach(el -> errors.addAll(el.valid()));
		return errors;
	}
	
	public String forDetail() {
		return "Sekcja #" + order;
	}
	
	@PostLoad
	private void init() {
		selectedElementsIds = new ArrayList<>();
		sectionType.fill(this);
	}
	
	@PreUpdate
	private void beforeUpdate() {
		sectionType.propagate(this);
	}

	@Override
	public Section copy(Section copy) {
		copy.title = this.title;
		copy.description = this.description;
		copy.order = this.order;
		copy.sectionType = this.sectionType;
		copy.elements = new ArrayList<>();
		
		if (elements != null) {
			elements.forEach(el -> copy.elements.add(el.copy(new Element(copy))));
		}
		
		return copy;
	}
}
