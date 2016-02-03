package pw.mario.journal.action.article.plain;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.event.SelectEvent;

import lombok.extern.log4j.Log4j;
import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Button;

@Log4j
@Button
@Dependent
public class TestMail implements ButtonAction {
	private static final long serialVersionUID = 1L;
	@Resource(mappedName = "java:jboss/mail/Gmail")
	private Session mailSession;
	
	@Override
	public boolean allowed() {
		return true;
	}

	@Override
	public void doAction() throws PerformActionException {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress("TEST_PW_JOURNAL");
			Address[] to = new InternetAddress[] {new InternetAddress("mariusz.raczynski20@gmail.com") };
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("Wildfly 9 Mail");
			m.setSentDate(new java.util.Date());
			m.setContent("Mail sent from JBoss AS 7","text/plain");
			Transport.send(m);
		} catch (MessagingException e) {
			log.error("Nie udało się wysłać maila", e);
		}
	}

	@Override
	public String getAction() {
		return null;
	}

	@Override
	public String getValue() {
		return "Test mail";
	}

	@Override
	public String getId() {
		return "bSendMailTest";
	}

	@Override
	public void onReturnEvent(SelectEvent e) {
	}

	@Override
	public void setArticle(Article a) {
	}

	@Override
	public boolean refreshNeeded() {
		return false;
	}

}
