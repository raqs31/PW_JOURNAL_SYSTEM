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
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Strings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.common.api.Modifiable;
import pw.mario.journal.model.AuditTable;
import pw.mario.journal.model.IdTable;

@Data
@EqualsAndHashCode(of = "formId", callSuper = false)
@ToString(exclude = "sections")
@NoArgsConstructor
@Entity
@Table(name = "FORMS", indexes = { @Index(columnList = "PATTERN") })
@NamedQueries({
	@NamedQuery(name=Form.Queries.PATTERN_FORMS,
			query="select f from Form f where f.pattern = true"),
	@NamedQuery(name=Form.Queries.NAMED_PATTERN,
		query="select f from Form f where f.pattern = true and f.patternCode = ?1")
})

public class Form extends AuditTable implements Modifiable, IdTable {
	@Id
	@Column(name = "FORM_ID")
	@SequenceGenerator(name = "formIdSeq", sequenceName = "FORMS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "formIdSeq", strategy = GenerationType.SEQUENCE)
	private Long formId;

	@Column(name = "NAME", length = 120)
	private String name;

	@Column(name = "PATTERN", nullable = false)
	private Boolean pattern;

	@OrderBy("order")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "form", orphanRemoval=true)
	private List<Section> sections;

	@Column(name = "LONG_ATTR1", length = 4000)
	private String longAttr1;

	@Column(name = "LONG_ATTR2", length = 4000)
	private String longAttr2;
	
	@Column(name = "PATTERN_CODE", length = 40)
	@Enumerated(EnumType.STRING)
	private PatternCode patternCode;

	@Override
	public void addChild() {
		if (sections == null)
			sections = new ArrayList<>();
		Section s = new Section(this);
		s.setOrder(sections.size());
		
		sections.add(s);

	}

	@Override
	public void delete() {
		if (sections != null)
			sections.clear();
	}

	@Override
	public void order() {
		int i = 0;
		if (sections != null)
			for (Section e : sections) {
				e.setOrder(i++);
			}

	}

	public interface Queries {
		String PATTERN_FORMS = "forms.pattern.all";
		String NAMED_PATTERN = "forms.pattern.named";
	}
	
	public enum PatternCode {
		ARTICLE_ACCEPTOR;
	}
	

	@Override
	public Object getId() {
		return formId;
	}

	@Override
	public List<FacesMessage> valid() {
		List<FacesMessage> errors = new LinkedList<>();
		if (Strings.isNullOrEmpty(name))
			errors.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie wprowadzono nazwy formularza", null));
		if (sections != null)
			sections.forEach(el -> errors.addAll(el.valid()));
		return errors;
	}
	
	public String getAllActiveIndex() {
		StringBuilder sb = new StringBuilder();
		int size = sections.size();
		
		for (int i = 0; i < size; i++)
			sb.append(i).append(',');
		if (size > 0)
			sb.setLength(sb.length() -1);
		return sb.toString();
		
	}
	
	public void setAllActiveIndex(String s) {}


}
