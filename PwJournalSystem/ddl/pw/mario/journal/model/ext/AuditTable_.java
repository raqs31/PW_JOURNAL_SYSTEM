package pw.mario.journal.model.ext;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-25T23:26:55.414+0100")
@StaticMetamodel(AuditTable.class)
public class AuditTable_ {
	public static volatile SingularAttribute<AuditTable, Date> creationDate;
	public static volatile SingularAttribute<AuditTable, Date> lastUpdateDate;
	public static volatile SingularAttribute<AuditTable, Long> objectVersionNumber;
	public static volatile SingularAttribute<AuditTable, String> createdBy;
	public static volatile SingularAttribute<AuditTable, String> lastUpdateBy;
}
