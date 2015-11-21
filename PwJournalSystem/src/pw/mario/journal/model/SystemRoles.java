package pw.mario.journal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
public class SystemRoles {
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
