package pw.mario.common.api;

import java.util.Collection;
import java.util.Date;

import javax.mail.internet.InternetAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Data
@EqualsAndHashCode(exclude={"to", "toCC", "toBCC"})
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class MailContext {
	private InternetAddress from;
	private @Singular("to") Collection<InternetAddress> to;
	private @Singular("toCC") Collection<InternetAddress> toCC;
	private @Singular("toBCC") Collection<InternetAddress> toBCC;
	private String subject;
	private String content;
	private Date sentDate;
}
