package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2015-12-29T15:05:30.790+0100")
@StaticMetamodel(User.class)
public class User_ extends AuditTable_ {
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> passwd;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> secondName;
	public static volatile ListAttribute<User, SystemRole> systemRoles;
	public static volatile SingularAttribute<User, Department> dept;
	public static volatile SingularAttribute<User, Boolean> isActive;
	public static volatile SingularAttribute<User, Long> userId;
}
