package pw.mario.journal.service.mail.impl;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pw.mario.journal.service.mail.MailContext;
import pw.mario.journal.service.mail.MailService;

@ApplicationScoped
public class MailServiceImpl implements MailService {
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;
	
	@Override
	public void send(MailContext mailCtx) {
		try    {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = mailCtx.getFrom();
            Address[] to = mailCtx.getTo().toArray(new Address[1]);

            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(mailCtx.getSubject());
            m.setSentDate(mailCtx.getSentDate());
            m.setContent(mailCtx.getContent(), "text/plain");
            Transport.send(m);
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
        }
	}

}
