package pw.mario.journal.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pw.mario.journal.model.ext.AuditTable;
import pw.mario.journal.model.ext.IdTable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="DEPARTMENTS", schema="MARIO")
public class Department extends AuditTable implements IdTable {
	@Id
	@Column(name="DEPT_ID")
	@SequenceGenerator(name="deptIdSeq", sequenceName="DEOARTMENTS_SEQ", allocationSize=1,initialValue=1)
	@GeneratedValue(generator="deptIdSeq", strategy=GenerationType.SEQUENCE)
	private long deptId;
	
	@Column(name="DEPT_CODE", length=30, nullable=false, unique=true)
	private String deptCode;
	
	@Column(name="FULL_NAME", length=120)
	private String fullName;
	
	@Column(name="DESCRIPTION", length=1000)
	private String description;
	
	@Column(name="IS_ACTIVE", nullable=false)
	private Boolean isActive;
	
	{
		isActive = true;
	}

	@Override
	public Object getId() {
		return deptId;
	}
}
