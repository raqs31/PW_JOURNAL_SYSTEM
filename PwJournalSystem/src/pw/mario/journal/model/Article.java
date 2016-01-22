package pw.mario.journal.model;

import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pw.mario.journal.model.dictionaries.ArticleStatus;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="ARTICLES", schema="MARIO", indexes={
		@Index(columnList="YEAR", unique=false),
		@Index(columnList="YEAR,MONTH,DAY", unique=false),
		@Index(columnList="MANAGEMENT_USER_ID", unique=false)
})
@NamedQueries({
	@NamedQuery(name=Article.Queries.USER_ARTICLES,
		query="select a from Article a "
				+ "join a.authors u "
				+ "where u.userId = ?1"
	)
})
public class Article extends AuditTable implements IdTable {
	@Id
	@Column(name="ARTICLE_ID")
	@SequenceGenerator(name="articleSeq", sequenceName="ARTICLES_SEQ", initialValue=1)
	@GeneratedValue(generator="articleSeq", strategy=GenerationType.SEQUENCE)
	private long articleId;

	@Column(name="NAME", length=30)
	private String name;
	
	@Column(name="DESCRIPTION", length=1024)
	private String description;
	
	@Column(name="YEAR", precision=0, nullable=false)
	private Integer year;
	
	@Column(name="MONTH", precision=0, nullable=false)
	private Integer month;
	
	@Column(name="DAY", precision=0, nullable=false)
	private Integer day;
	
	@ManyToOne
	@JoinColumn(name="MANAGEMENT_USER_ID", referencedColumnName="USER_ID",  updatable=true, nullable=false)
	private User management;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="AUTHORS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")},
			indexes={@Index(columnList="ARTICLE_ID"), @Index(columnList="USER_ID")}
	)
	private Set<User> authors;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="ARTICLE_TAGS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="TAG_ID", referencedColumnName="TAG_ID", nullable=false)},
			indexes={@Index(columnList="ARTICLE_ID"), @Index(columnList="TAG_ID")}
	)
	private Set<Tag> tagList;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="article", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ArticleVersion> versions;

	
	@Override
	public Object getId() {
		return getArticleId();
	}
	
	public interface Queries {
		String USER_ARTICLES = "user.articles";
	}
}
