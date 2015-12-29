package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="ARTICLE_VERSIONS", schema="MARIO", 
	indexes={@Index(columnList="ARTICLE_ID, LAST_VERSION", unique=false),
			@Index(columnList="STATUS")
	}
)
public class ArticleVersion extends AuditTable implements IdTable {
	@Id
	@SequenceGenerator(name="versionSeq", sequenceName="VERSIONS_SEQ", initialValue=1)
	@GeneratedValue(generator="versionSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="VERSION_ID")
	private long versionId;
	
	@Column(name="VERSION_NUM", precision=0, nullable=false)
	private long versionNum;
	
	@Column(name="LAST_VERSION")
	private Boolean lastVersion;
	
	@Column(name="STATUS", length=20, nullable=false)
	private String status;
	
	@Column(name="ATTACHEMENT")
	private String attachement;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTICLE_ID", nullable=false, updatable=false)
	private Article article;

	@Override
	public Object getId() {
		return versionId;
	}
}
