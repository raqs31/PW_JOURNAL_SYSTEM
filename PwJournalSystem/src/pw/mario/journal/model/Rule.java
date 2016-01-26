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
			query = "select r from Rule r, User u, SystemRole sr where u = ?1 and r.fromStatus = ?2 and sr.roleName = r.forRole.roleName")
})
public class Rule implements Serializable, IdTable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="routingRulesSeq", initialValue = 1, allocationSize= 1,sequenceName="ROUTING_RULES_SEQ")
	@GeneratedValue(generator="routingRulesSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="RULE_ID")
	private Long ruleId;
	
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

	@Column(name="FOR_AUTHOR",nullable=false)
	private boolean forAuthor;
	
	@Column(name="FOR_MANAGER", nullable=false)
	private boolean forManager;
	
	@Column(name="FOR_ACCEPTOR",nullable=false)
	private boolean forAcceptor;
	
	@Column(name="PICK_ACCEPTORS",nullable=false)
	private boolean pickAcceptors;
	
	@Override
	public Object getId() {
		return ruleId;
	}
	
	public interface Queries {
		String NEXT_STEPS = "article.rule.next";
	}
}
