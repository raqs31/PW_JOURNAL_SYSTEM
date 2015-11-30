package pw.mario.journal.model.ext;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@MappedSuperclass
public class AuditTable {
	@Column(name = "CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "LAST_UPDATE_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	@Column(name = "OVN", nullable = false)
	private long objectVersionNumber;

	@Column(name = "CREATED_BY", nullable = false)
	private long createdBy;

	@Column(name = "LAST_UPDATED_BY", nullable = false)
	private long lastUpdateBy;

	@PrePersist
	public void beforeInsert() {
		Date current = Calendar.getInstance().getTime();
		setCreationDate(current);
		setLastUpdateDate(current);
		setCreationDate(current);
		setObjectVersionNumber(1);
		//TODO zmieni� na konkretnego usera
		setCreatedBy(-1L);
		setLastUpdateBy(-1L);
	}

	@PreUpdate
	public void beforeUpdate() {
		Date current = Calendar.getInstance().getTime();
		setLastUpdateDate(current);
		//TODO zmieni� na konkretnego usera
		setLastUpdateBy(-1L);
	}

}
