package pw.mario.journal.model.form;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Strings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.common.api.Copyable;
import pw.mario.common.api.Modifiable;

@Data
@EqualsAndHashCode(of="elemId")
@ToString(exclude="section")
@NoArgsConstructor
@Entity
@Table(name="SECTION_ELEMENTS")
public class Element implements Modifiable, Copyable<Element> {
	
	@Id
	@Column(name="ELEM_ID")
	@SequenceGenerator(name = "formElemId", sequenceName = "SECTION_ELEMENTS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="formElemId", strategy=GenerationType.SEQUENCE)
	private Long elemId;
	
	@Column(name="DESCRIPTION", length=120)
	private String description;
	
	@Column(name="VALUE", length=4000)
	private String value;
	
	@Column(name="ORDER_NUM")
	private Integer order;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="SECTION_ID", referencedColumnName="SECTION_ID", nullable=false)
	private Section section;

	@Override
	public void addChild() {
	}

	@Override
	public void delete() {
		getSection().getElements().remove(this);
	}
	
	public Element(Section s) {
		section = s;
	}

	@Override
	public void order() {
	}

	@Override
	public List<FacesMessage> valid() {
		List<FacesMessage> errors = new LinkedList<>();
		if (Strings.isNullOrEmpty(description))
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie wprowadzono opisu", forDetail()));
		return errors;
	}
	
	public String forDetail() {
		return section.forDetail() + " -> element #" + order;
	}

	@Override
	public Element copy(Element copy) {
		copy.description = this.description;
		copy.order = this.order;
		copy.value = this.value;
		
		return copy;
	}
}
