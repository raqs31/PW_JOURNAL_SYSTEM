package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name="ARTICLE_HISTORY")
public class ArticleHistory extends AuditTable implements IdTable {
	@Id
	@Column(name="HISTORY_ID")
	private Long id;
	
	@Column(name="USER_COMMENT", length=120, nullable=true)
	private String comment;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID")
	private Article article;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="RULE_ID", referencedColumnName="RULE_ID")
	private Rule rule;
}
