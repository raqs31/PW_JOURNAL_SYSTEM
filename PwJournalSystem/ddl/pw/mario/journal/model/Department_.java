package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2016-01-25T23:26:55.393+0100")
@StaticMetamodel(Department.class)
public class Department_ extends AuditTable_ {
	public static volatile SingularAttribute<Department, Long> deptId;
	public static volatile SingularAttribute<Department, String> deptCode;
	public static volatile SingularAttribute<Department, String> fullName;
	public static volatile SingularAttribute<Department, String> description;
	public static volatile SingularAttribute<Department, Boolean> isActive;
}
