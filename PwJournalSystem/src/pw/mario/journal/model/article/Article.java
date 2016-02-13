package pw.mario.journal.model.article;

import java.util.List;
import java.util.NoSuchElementException;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pw.mario.journal.model.common.Tag;
import pw.mario.journal.model.common.User;
import pw.mario.journal.model.dictionary.ArticleStatus;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@ToString(exclude={"authors", "versions", "tagList", "acceptors", "history"})
@EqualsAndHashCode(callSuper=false, of={"articleId"})
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
	),
	@NamedQuery(name=Article.Queries.ARTICLE_AUTHORS,
		query="select u from Article a join a.authors u where a.articleId = ?1"
	),
	@NamedQuery(name=Article.Queries.ARTICLE_ACCEPTORS,
		query="select acc from ArticleAcceptor acc where acc.article = ?1"
	),
	@NamedQuery(name=Article.Queries.ARTICLE_TAGS,
		query="select t from Article a join a.tagList t where a.articleId = ?1"
	),
	@NamedQuery(name=Article.Queries.MANAGER_ARTICLE,
		query="select a from Article a where a.management = ?1"
	),
	@NamedQuery(name=Article.Queries.ACCEPTOR_ARTICLE,
		query="select distinct acc.article from ArticleAcceptor acc where acc.acceptor = ?1"
	),
	@NamedQuery(name=Article.Queries.PRINTABLE_ARTICLE,
		query="select a from Article a where a.status.attr3 = '" + ArticleStatus.PRINTABLE + "'"
	)
})
public class Article extends AuditTable implements IdTable {
	@Id
	@Column(name="ARTICLE_ID")
	@SequenceGenerator(name="articleSeq", sequenceName="ARTICLES_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="articleSeq", strategy=GenerationType.SEQUENCE)
	private Long articleId;

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
	@JoinColumn(name="MANAGEMENT_USER_ID", referencedColumnName="USER_ID")
	private User management;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="AUTHORS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")}
	)
	private Set<User> authors;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="ARTICLE_TAGS",
			joinColumns={@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="TAG_ID", referencedColumnName="TAG_ID", nullable=false)}
	)
	private Set<Tag> tagList;
	
	@OrderBy("versionNum DESC")
	@OneToMany(fetch=FetchType.LAZY,mappedBy="article", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ArticleVersion> versions;
	
	@OneToMany(mappedBy="article", fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	private List<ArticleAcceptor> acceptors;
	

	@ManyToOne
	@JoinColumn(name="ID_STATUS", referencedColumnName="DICT_ID")
	private ArticleStatus status;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="article", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ArticleHistory> history;
	
	@Override
	public Object getId() {
		return getArticleId();
	}
	
	public interface Queries {
		String USER_ARTICLES = "user.articles";
		String ARTICLE_AUTHORS = "article.authors";
		String ARTICLE_TAGS = "article.tags";
		String ACCEPTOR_ARTICLE = "article.acceptor";
		String MANAGER_ARTICLE = "article.manager";
		String PRINTABLE_ARTICLE = "article.print";
		String ARTICLE_ACCEPTORS = "article.acceptors";
	}
	
	public ArticleAcceptor getArticleAcceptor(User u) {
		try {
			return acceptors.stream()
				.filter(acc -> acc.getApply() == false && acc.getAcceptor().getUserId().compareTo(u.getUserId())==0)
				.limit(1)
				.findFirst()
				.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
