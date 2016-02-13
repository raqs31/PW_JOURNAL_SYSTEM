package pw.mario.journal.model.article;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.AuditTable;
import pw.mario.journal.model.IdTable;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name="ARTICLE_HISTORY")
public class ArticleHistory extends AuditTable implements IdTable {
	@Id
	@SequenceGenerator(sequenceName="ARTICLE_HISTORY_SEQ", name="articleHistorySeq", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="articleHistorySeq", strategy=GenerationType.SEQUENCE)
	@Column(name="HISTORY_ID")
	@OrderBy("id desc")
	private Long id;
	
	@Column(name="USER_COMMENT", length=120, nullable=true)
	private String comment;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID")
	private Article article;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="RULE_ID", referencedColumnName="RULE_ID")
	private Rule rule;
	
	@OneToOne(optional=true, cascade={CascadeType.MERGE})
	@JoinColumn(name="VERSION_ID", referencedColumnName="VERSION_ID")
	private ArticleVersion version;
	
	@Transient
	private String description;

	@PostLoad
	@PostPersist
	private void afterLoad() {
		StringBuilder sb = new StringBuilder();
		if (rule != null)
			sb.append("Wykonano akcję: ")
				.append(rule.getName())
				.append(' ');
		if (version != null)
			sb.append("Dodano plik: ")
				.append( version.getAttachement().substring(version.getAttachement().lastIndexOf('\\') + 1));
		description = sb.toString();
	}
}
