package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	indexes={
		@Index(columnList="ARTICLE_ID, LAST_VERSION", unique=false)
	}
)
@NamedQueries({
	@NamedQuery(name=ArticleVersion.Queries.ARTICLE_VERSION, 
			query="select v from ArticleVersion v where v.article.articleId = ?1 order by v.versionNum desc")
})
public class ArticleVersion extends AuditTable implements IdTable {
	public interface Queries {
		String ARTICLE_VERSION = "Article.Version";
	}
	@Id
	@SequenceGenerator(name="versionSeq", sequenceName="VERSIONS_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="versionSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="VERSION_ID")
	private Long versionId;
	
	@Column(name="VERSION_NUM", precision=0, nullable=false)
	private Long versionNum;
	
	@Column(name="LAST_VERSION")
	private Boolean lastVersion;
	
	@Column(name="ATTACHEMENT")
	private String attachement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTICLE_ID", nullable=false, updatable=false)
	private Article article;
	
	@OneToOne(mappedBy="version", optional=true)
	private ArticleAcceptor acceptor;
	
	@Override
	public Object getId() {
		return versionId;
	}
}
