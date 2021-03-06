
package pw.mario.journal.model.common;

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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.mario.journal.model.AuditTable;
import pw.mario.journal.model.IdTable;

@Data
@ToString(exclude={"systemRoles"})
@EqualsAndHashCode(callSuper=false, of={"userId"})
@NoArgsConstructor
@Cacheable(true)
@Entity
@Table(name="USERS",
	indexes={
			@Index(columnList="LOGIN", unique=true),
			@Index(columnList="EMAIL", unique=true),
			@Index(columnList="DEPARTMENT_ID", unique=false)
	}
)
@NamedQueries({
	@NamedQuery(name=User.Queries.GET_BY_LOGIN, 
			query = "select u from User u where upper(u.login) = ?1"),
	@NamedQuery(name=User.Queries.GET_BY_EMAIL, 
		query = "select u from User u where upper(u.email) = ?1"),
	@NamedQuery(name=User.Queries.USERS_WITH_DEPARTMENT_ROLE,
		query = "select u from User u join u.systemRoles sr where ((?1 is null and u.dept is null) or u.dept.deptId =  ?1) and sr.roleName = ?2"),
	@NamedQuery(name=User.Queries.USERS_NOT_CROSSED_AUTHORS_AND_MNG,
		query = "select u from User u join u.systemRoles sr where sr.roleName = ?2 and not exists (select 1 from Article a join a.authors au where a.articleId = ?1 and (au = u or au.dept = u.dept or a.management = u))"),
	@NamedQuery(name=User.Queries.USERS_NOT_CROSSED_AUTHORS,
	query = "select u from User u join u.systemRoles sr where sr.roleName = ?2 and not exists (select 1 from Article a join a.authors au where a.articleId = ?1 and (au = u or au.dept = u.dept))")

})
public class User extends AuditTable implements IdTable, Comparable<User> {
	public interface Queries {
		String GET_BY_LOGIN = "User.getByLogin";
		String GET_BY_EMAIL = "User.getByEmail";
		String USERS_WITH_DEPARTMENT_ROLE = "User.getWithDeptAndRole";
		String USERS_NOT_CROSSED_AUTHORS_AND_MNG = "user.not.crossed.authors.mng";
		String USERS_NOT_CROSSED_AUTHORS = "user.not.crossed.authors";
	}
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="userSeq", sequenceName ="USERS_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="userSeq", strategy=GenerationType.SEQUENCE)
	private Long userId;
	
	
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
	

	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="USER_SYSTEM_ROLES",	
		joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID", unique=false)},
		inverseJoinColumns={@JoinColumn(name="SYSTEM_ROLE_ID", referencedColumnName="SYSTEM_ROLE_ID", unique=false)},
		indexes={@Index(columnList="USER_ID, SYSTEM_ROLE_ID", unique=true)}
	)
	private List<SystemRole> systemRoles;
	
	@OneToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_ID")
	private Department dept;
	
	@Column(name="IS_ACTIVE", nullable=false)
	private Boolean isActive;
	
	{
		isActive=true;
	}
	@Override
	public Object getId() {
		return userId;
	}
	@Override
	public int compareTo(User o) {
		if (this.equals(o))
			return 0;
		return o.userId.compareTo(this.userId);
	}
}
