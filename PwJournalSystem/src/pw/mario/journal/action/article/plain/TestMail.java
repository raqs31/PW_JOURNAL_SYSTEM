package pw.mario.journal.action.article.plain;


import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.event.SelectEvent;

import pw.mario.common.action.form.ButtonAction;
import pw.mario.common.api.Refreshable;
import pw.mario.common.exception.PerformActionException;
import pw.mario.journal.model.Article;
import pw.mario.journal.qualifiers.Button;

@Button
@Dependent
public class TestMail implements ButtonAction {
	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;
	
	@Override
	public boolean allowed() {
		return true;
	}

	@Override
	public void doAction() throws PerformActionException {
		try    {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress("raqs31@gmail.com");
            Address[] to = new InternetAddress[] {new InternetAddress("raqs31@gmail.com") };

            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject("Test mail krebsio");
            m.setSentDate(new java.util.Date());
            m.setContent("Mail sent from JBoss AS 7","text/plain");
            Transport.send(m);
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public String getAction() {
		return null;
	}

	@Override
	public String getValue() {
		return "Test mail do mnie";
	}

	@Override
	public String getId() {
		return "bTestMail";
	}

	@Override
	public void onReturnEvent(SelectEvent e) {
	}

	@Override
	public String ajax() {
		return "false";
	}

	@Override
	public void setArticle(Article a) {
	}

	@Override
	public void setToRefresh(Refreshable toRefresh) {
	}
}
