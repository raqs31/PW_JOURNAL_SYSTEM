package pw.mario.journal.model.article;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pw.mario.journal.model.AuditTable;
import pw.mario.journal.model.IdTable;
import pw.mario.journal.model.common.User;
import pw.mario.journal.model.dictionary.AcceptorStatus;
import pw.mario.journal.model.form.Form;

@Data
@ToString(of="articleAcceptorId")
@EqualsAndHashCode(callSuper=false, of="articleAcceptorId")
@Entity
@Table(name="ARTICLE_ACCEPTORS", schema="MARIO", indexes={
		@Index(columnList="ARTICLE_ID, USER_ID", unique=false)
})
public class ArticleAcceptor extends AuditTable implements IdTable {
	@Id
	@Column(name="ARTICLE_ACCEPTOR_ID")
	@SequenceGenerator(name="articleAcceptorsSeq", sequenceName="ARTICLE_ACCEPTORS_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="articleAcceptorsSeq", strategy=GenerationType.SEQUENCE)
	private Long articleAcceptorId;

	@ManyToOne
	@JoinColumn(name="ACCEPTOR_STATE_ID", referencedColumnName="DICT_ID", nullable=true)
	private AcceptorStatus state;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID", nullable=false)
	private User acceptor;
	
	@OneToOne(optional=true)
	@JoinColumn(name="VERSION_ID", referencedColumnName="VERSION_ID", nullable=true)
	private ArticleVersion version;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ARTICLE_ID", referencedColumnName="ARTICLE_ID", nullable=false, unique=false)
	private Article article;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=true, orphanRemoval=true)
	@JoinTable(name="ACCEPTOR_FORM",
			joinColumns={@JoinColumn(name="ARTICLE_ACCEPTOR_ID", referencedColumnName="ARTICLE_ACCEPTOR_ID", unique=true)},
			inverseJoinColumns={@JoinColumn(name="FORM_ID", referencedColumnName="FORM_ID", unique=true)}
			)
	private Form acceptorForm;
	
	@Column(name="IS_APPLY", nullable=false)
	private Boolean apply;
	
	@Override
	public Object getId() {
		return articleAcceptorId;
	}
	
	public boolean isEditable(User u) {
		return !apply && isAcceptor(u);
	}
	
	public boolean isAcceptor(User u) {
		return u != null && u.getUserId().compareTo(acceptor.getUserId()) == 0;
	}
}
