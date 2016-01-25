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
		@Index(columnList="FROM_STATUS, FOR_ROLE")
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
	@JoinColumn(name="FOR_ROLE")
	private SystemRole forRole;

	@Override
	public Object getId() {
		return ruleId;
	}
   
}