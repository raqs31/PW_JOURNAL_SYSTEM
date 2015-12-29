package pw.mario.journal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="ARTICLES", schema="MARIO")
public class Article extends AuditTable implements IdTable {
	@Id
	@Column(name="ARTICLE_ID")
	@SequenceGenerator(name="articleSeq", sequenceName="ARTICLES_SEQ", initialValue=1)
	@GeneratedValue(generator="articleSeq", strategy=GenerationType.SEQUENCE)
	private long articleId;

	@ManyToOne
	@JoinColumn(name="MANAGEMENT_USER_ID", referencedColumnName="USER_ID",  updatable=true, nullable=false)
	private User managementId;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="AUTHORS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")},
			indexes={@Index(columnList="ARTICLE_ID"), @Index(columnList="USER_ID")}
	)
	private List<User> authors;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="ARTICLE_TAGS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="TAG_ID", referencedColumnName="TAG_ID", nullable=false)},
			indexes={@Index(columnList="ARTICLE_ID"), @Index(columnList="TAG_ID")}
	)
	private List<Tag> tagList;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="article", cascade=CascadeType.ALL)
	private List<ArticleVersion> versions;

	@Override
	public Object getId() {
		return getArticleId();
	}
}
