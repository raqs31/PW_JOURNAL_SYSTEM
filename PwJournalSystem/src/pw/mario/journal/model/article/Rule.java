package pw.mario.journal.model.article;

import java.io.Serializable;
import java.util.Set;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.journal.model.common.SystemRole;
import pw.mario.journal.model.dictionary.AcceptorStatus;
import pw.mario.journal.model.dictionary.ArticleStatus;
import pw.mario.journal.model.dictionary.ValidationRule;
import pw.mario.journal.model.ext.IdTable;

/**
 * Entity implementation class for Entity: Rule
 *
 */
@Data
@ToString(exclude={"forRole", "validations"})
@EqualsAndHashCode(of={"ruleId"})
@Entity
@NoArgsConstructor
@Table(name="ROUTING_RULES", indexes={
		@Index(columnList="FROM_STATUS, FOR_ROLE_NAME"),
		@Index(columnList="IS_ACTIVE")
	})
@NamedQueries({
	@NamedQuery(name=Rule.Queries.NEXT_STEPS,
			query = "select r "
					+ "from Rule r, "
					+ "User u, "
					+ "Article a, "
					+ "SystemRole sr "
					+ "where u = ?1 "
					+ "and a = ?2 "
					+ "and sr member of u.systemRoles "
					+ "and r.fromStatus = a.status "
					+ "and r.forRole.roleName = sr.roleName "
					+ "and (r.forAuthor = false or (r.forAuthor = true and u member of a.authors)) "
					+ "and (r.forAcceptor = false or (r.forAcceptor = true and exists (select 1 from a.acceptors acc where acc.apply = false and acc.acceptor = u and acc.state is null))) "
					+ "and (r.forManager = false or (r.forManager = true and u = a.management)) "
					+ "and r.isActive = true "
					+ "and (r.acceptorState = false or (r.acceptorState = true and not exists (select 1 from a.acceptors acc where acc.apply = false))) "
			)
})
public class Rule implements Serializable, IdTable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="routingRulesSeq", initialValue = 1, allocationSize= 1,sequenceName="ROUTING_RULES_SEQ")
	@GeneratedValue(generator="routingRulesSeq", strategy=GenerationType.SEQUENCE)
	@Column(name="RULE_ID")
	private Long ruleId;
	
	@Column(name="RULE_CODE", unique=true, nullable=false)
	private String code;
	
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
	
	@Column(name="PICK_MANAGER", nullable=false)
	private Boolean pickManager;
	
	@Column(name="READY_TO_PRINT", nullable=false)
	private Boolean readyToPrin;
	
	@Column(name="CLEAR_MANAGER", nullable=false)
	private Boolean clearManager;
	
	@Column(name="CLEAR_ACCEPTORS", nullable=false)
	private Boolean clearAcceptors;
	
	@Column(name="IS_ACTIVE", nullable=false)
	private Boolean isActive;
	
	@Column(name="SET_ACCEPTOR_STATUS", nullable=false)
	private Boolean setAcceptorStatus;
	
	@ManyToOne
	@JoinColumn(name="ACCEPT_STATUS", nullable=true)
	private AcceptorStatus acceptorStatus;
	
	@Column(name="FROM_ACCEPTORS_STATE", nullable=false)
	private Boolean acceptorState;
	
	@Column(name="APPLY_ACCEPTORS_STATE", nullable=false)
	private Boolean applyAcceptorsState;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="RULES_VALIDATIONS",
			joinColumns={@JoinColumn(name="RULE_ID", referencedColumnName="RULE_ID")},
			inverseJoinColumns={@JoinColumn(name="DICT_ID", referencedColumnName="DICT_ID")}
	)
	private Set<ValidationRule> validations;
	
	@Override
	public Object getId() {
		return ruleId;
	}
	
	public interface Queries {
		String NEXT_STEPS = "article.rule.next";
	}
	
	public boolean withUserAction() {
		return pickAcceptors || pickManager;
	}
}
