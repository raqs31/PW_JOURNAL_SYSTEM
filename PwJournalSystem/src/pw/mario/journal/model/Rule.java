package pw.mario.journal.model;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.SystemRole;
import pw.mario.journal.model.dictionaries.ArticleStatus;
import pw.mario.journal.model.ext.IdTable;

/**
 * Entity implementation class for Entity: Rule
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="ROUTING_RULES", indexes={
		@Index(columnList="FROM_STATUS, FOR_ROLE_NAME")
	})
@NamedQueries({
	@NamedQuery(name=Rule.Queries.NEXT_STEPS,
			query = "select r from Rule r, User u, SystemRole sr, Article a where u = ?1 and a = ?2 and r.fromStatus = a.status and sr.roleName = r.forRole.roleName and (r.forAuthor = false or (r.forAuthor = true and u member of a.authors)) and (r.forAcceptor = false or (r.forAcceptor = true and u member of a.acceptors)) and (r.forManager = false or (r.forManager = true and u = a.management)) "
			)
})
public class Rule implements Serializable, IdTable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="routingRulesSeq", initialValue = 1, allocationSize= 1,sequenceName="ROUTING_RULES_SEQ")
	@GeneratedValue(generator="routingRulesSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="RULE_ID")
	private Long ruleId;
	
	@OrderBy("name asc")
	@Column(name="NAME", length=64)
	private String name;
	
	@Column(name="DESCRIPTION", length=120)
	private String description;
	
	
	@ManyToOne(targetEntity=ArticleStatus.class, optional=false)
	@JoinColumn(name="FROM_STATUS")
	private ArticleStatus fromStatus;

	@ManyToOne(targetEntity=ArticleStatus.class, optional=false)
	@JoinColumn(name="TO_STATUS")
	private ArticleStatus toStatus;
	
	
	@ManyToOne(targetEntity=SystemRole.class, optional=true)
	@JoinColumn(name="FOR_ROLE_NAME", referencedColumnName="ROLE_NAME")
	private SystemRole forRole;

	@Column(name="FOR_AUTHOR", nullable=false)
	private Boolean forAuthor;
	
	@Column(name="FOR_MANAGER", nullable=false)
	private Boolean forManager;
	
	@Column(name="FOR_ACCEPTOR", nullable=false)
	private Boolean forAcceptor;
	
	@Column(name="PICK_ACCEPTORS", nullable=false)
	private Boolean pickAcceptors;
	
	@Column(name="READY_TO_PRINT", nullable=false)
	private Boolean readyToPrin;
		
	@Override
	public Object getId() {
		return ruleId;
	}
	
	public interface Queries {
		String NEXT_STEPS = "article.rule.next";
	}
}
