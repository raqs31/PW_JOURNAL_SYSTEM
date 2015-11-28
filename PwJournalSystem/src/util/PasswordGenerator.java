package util;

import org.jboss.security.auth.spi.Util;


public class PasswordGenerator {
	
		public static void main(String[] args) {
			for (String arg : args)
				System.out.println(new PasswordGenerator().generate(arg));
		}
	   public String generate(String password) {
	     return Util.createPasswordHash("SHA-256", "BASE64", null, null,password);
	   }
}
