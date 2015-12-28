package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-28T15:59:37.684+0100")
@StaticMetamodel(SystemRole.class)
public class SystemRole_ {
	public static volatile SingularAttribute<SystemRole, Long> sysRoleId;
	public static volatile SingularAttribute<SystemRole, String> roleName;
	public static volatile SingularAttribute<SystemRole, String> description;
	public static volatile SingularAttribute<SystemRole, Boolean> isActive;
}
