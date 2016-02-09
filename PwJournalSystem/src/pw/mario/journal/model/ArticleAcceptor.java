package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pw.mario.journal.model.dictionaries.AcceptorStatus;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@ToString(of="articleAcceptorId")
@EqualsAndHashCode(callSuper=false, of="articleAcceptorId")
@Entity
@Table(name="ARTICLE_ACCEPTORS", schema="MARIO")
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
	
	@Column(name="IS_APPLY", nullable=false)
	private Boolean apply;
	
	@Override
	public Object getId() {
		return articleAcceptorId;
	}
	
}
