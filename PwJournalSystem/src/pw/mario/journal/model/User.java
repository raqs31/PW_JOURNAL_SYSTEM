package pw.mario.journal.model;

import java.util.List;

import javax.persistence.Cacheable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.ext.AuditTable;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Cacheable(true)
@Entity
@Table(name="USERS", schema="MARIO",
	indexes={
			@Index(columnList="login", unique=true),
			@Index(columnList="email", unique=true),
	}
)
@NamedQueries({
	@NamedQuery(name=User.Queries.GET_BY_LOGIN, 
			query = "select u from User u where u.login = ?1"),
	@NamedQuery(name=User.Queries.GET_BY_EMAIL, 
		query = "select u from User u where u.email = ?1")
})
public class User extends AuditTable {
	public interface Queries {
		String GET_BY_LOGIN = "User.getByLogin";
		String GET_BY_EMAIL = "User.getByEmail";
	}
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="userSeq", sequenceName ="USERS_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="userSeq", strategy=GenerationType.SEQUENCE)
	private long id;
	
	
	@Column(name="LOGIN", length=25, unique=true, nullable=false)
	private String login;
	
	@Column(name="EMAIL", length=60, unique=true, nullable=false)
	private String email;
	
	@Column(name="PASSWD", length=240, nullable=false)
	private String passwd;
	
	@Column(name="NAME", length=25, nullable=true)
	private String name;
	
	@Column(name="SECOND_NAME", length=25, nullable=true)
	private String secondName;
	

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="USER_SYSTEM_ROLES",
		joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")},
		inverseJoinColumns={@JoinColumn(name="SYSTEM_ROLE_ID", referencedColumnName="SYSTEM_ROLE_ID")},
		indexes={@Index(columnList="USER_ID", unique=false)}
	)
	private List<SystemRole> systemRoles;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="DEPARTMENT_ID")
	private Department dept;
	
	@Column(name="IS_ACTIVE", nullable=false)
	private Boolean isActive;
	
	{
		isActive=true;
	}

}