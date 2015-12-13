package pw.mario.journal.util;

import javax.enterprise.context.Dependent;

import org.jboss.security.auth.spi.Util;

@Dependent
public class MD5Passwd {
	public String generate(String password) {
		return Util.createPasswordHash("SHA-256", "BASE64", null, null, password);
	}
}
