package pw.mario.journal.service.mail;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import pw.mario.journal.model.common.User;


@Data
@EqualsAndHashCode(exclude={"to", "toCC", "toBCC"})
@NoArgsConstructor
public class MailContext {
	private InternetAddress from;
	private @Singular("to") Collection<InternetAddress> to;
	private @Singular("toCC") Collection<InternetAddress> toCC;
	private @Singular("toBCC") Collection<InternetAddress> toBCC;
	private String subject;
	private String content;
	private Date sentDate;
	
	public static class MailContextBuilder {
		private User from;
		private Map<Long, User> to;
		private String subject;
		private String content;
		
		public MailContextBuilder setFrom(User u) {
			this.from = u;
			return this;
		}
		
		public MailContextBuilder setSubject(String subject) {
			this.subject = subject;
			return this;
		}
		
		public MailContextBuilder setContent(String content) {
			this.content = content;
			return this;
		}
		
		public MailContextBuilder addTo(User u) {
			to.put(u.getUserId(), u);
			return this;
		}
		
		public MailContext build() throws AddressException {
			MailContext ctx = new MailContext();
			ctx.setFrom(new InternetAddress(from.getEmail()));
			ctx.to = new LinkedList<>();
			
			for (User u: to.values())
				ctx.to.add(new InternetAddress(u.getEmail()));
			
			ctx.setContent(content);
			ctx.setSubject(subject);
			
			return ctx;
		}
	}
}
