package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="SYSTEM_ROLES", schema="MARIO",
indexes={
		@Index(columnList="ROLE_NAME", unique=true),
		@Index(columnList="IS_ACTIVE", unique=false)
})
@NamedQueries({
	@NamedQuery(
			name="SystemRole.excludedRoles",
			query="select s from SystemRole s "
		 		+ "where not exists (select 1 from User u join u.systemRoles usr "
		 		+ "where u.id = :id and usr.sysRoleId = s.sysRoleId)" 
		 		)
})
public class SystemRole {
	@Id
	@Column(name="SYSTEM_ROLE_ID")
	@SequenceGenerator(name="sysRoleSeq", initialValue=1, sequenceName="SYSTEM_ROLES_SEQ")
	@GeneratedValue(generator="sysRoleSeq", strategy=GenerationType.SEQUENCE)
	private long sysRoleId;
	
	@Column(name="ROLE_NAME", unique=true, length=50)
	private String roleName;
	
	@Column(name="DESCRIPTION", length=4000)
	private String description;
	
	@Column(name="IS_ACTIVE")
	private boolean isActive;
}
