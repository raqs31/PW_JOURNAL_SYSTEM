package pw.mario.journal.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="USERS", schema="MARIO",
	indexes={
			@Index(columnList="login", unique=true),
			@Index(columnList="email", unique=true),
	}
)
public class User {
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="userSeq", sequenceName ="USERS_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="userSeq", strategy=GenerationType.SEQUENCE)
	private long id;
	
	
	@Column(name="LOGIN", length=25, unique=true, nullable=false)
	private String login;
	
	@Column(name="EMAIL", length=60, unique=true, nullable=false)
	private String email;
	
	@Column(name="NAME", length=25, nullable=true)
	private String name;
	
	@Column(name="SECOND_NAME", length=25, nullable=true)
	private String secondName;
	
	@Column(name="CREATION_DATE", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name="LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;
	
	@PrePersist
	public void beforeInsert() {
		setCreationDate(Calendar.getInstance().getTime());
		setLastUpdateDate(Calendar.getInstance().getTime());
	}
	
	@PreUpdate
	public void beforeUpdate() {
		setLastUpdateDate(Calendar.getInstance().getTime());
	}
}
