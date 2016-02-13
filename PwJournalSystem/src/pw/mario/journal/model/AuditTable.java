package pw.mario.journal.model;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
@MappedSuperclass
public class AuditTable {
	@Column(name = "CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "LAST_UPDATE_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	@Version
	@Column(name = "OVN", nullable = false)
	private Long objectVersionNumber;

	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;

	@Column(name = "LAST_UPDATED_BY", nullable = false)
	private String lastUpdateBy;

	@PrePersist
	private void beforeInsert() {
		Date current = Calendar.getInstance().getTime();
		setCreationDate(current);
		setLastUpdateDate(current);
		try {
			Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			setCreatedBy(user.getName().toUpperCase());	
			setLastUpdateBy(user.getName().toUpperCase());
		} catch (Exception e) {
			log.warn("Exception in prePersist", e);
			setCreatedBy("NULL");
			setLastUpdateBy("NULL");
		}
	}
	
	@PreUpdate
	private void beforeUpdate() {
		Date current = Calendar.getInstance().getTime();
		setLastUpdateDate(current);
		try {
			Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			setLastUpdateBy(user.getName().toUpperCase());	
		} catch (Exception e) {
			log.warn("Exception in prePersist", e);
			setLastUpdateBy("NULL");
		}
	}

}
