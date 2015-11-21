package pw.mario.journal.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pw.mario.journal.model.ext.AuditTable_;

@Generated(value="Dali", date="2015-11-21T21:28:13.049+0100")
@StaticMetamodel(User.class)
public class User_ extends AuditTable_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> passwd;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> secondName;
	public static volatile ListAttribute<User, SystemRoles> systemRoles;
}
