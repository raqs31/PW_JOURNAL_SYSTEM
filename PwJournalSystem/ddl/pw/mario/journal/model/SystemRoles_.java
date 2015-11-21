package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-17T23:56:56.185+0100")
@StaticMetamodel(SystemRoles.class)
public class SystemRoles_ {
	public static volatile SingularAttribute<SystemRoles, Long> sysRoleId;
	public static volatile SingularAttribute<SystemRoles, String> roleName;
	public static volatile SingularAttribute<SystemRoles, String> description;
	public static volatile SingularAttribute<SystemRoles, Boolean> isActive;
}
