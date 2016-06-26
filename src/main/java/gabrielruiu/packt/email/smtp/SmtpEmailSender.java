package gabrielruiu.packt.email.smtp;

import gabrielruiu.packt.email.EmailBodyProvider;
import gabrielruiu.packt.email.EmailSender;
import gabrielruiu.packt.exception.EmailPreparationException;
import gabrielruiu.packt.model.BookSummary;
import gabrielruiu.packt.properties.EmailProperties;
import gabrielruiu.packt.scheduler.EmailSubjectResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Profile("smtp")
@Component
public class SmtpEmailSender implements EmailSender {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailBodyProvider emailBodyProvider;

    @Autowired
    private EmailSubjectResolver emailSubjectResolver;

    @Autowired
    private EmailProperties emailProperties;

    public void sendEmailWithBookSummary(BookSummary bookSummary) {
        LOG.info(String.format("Sending email with following content: [%s]", bookSummary.toString()));
        MimeMessage mail = createEmail(bookSummary);
        javaMailSender.send(mail);
        LOG.info("Email sent");
    }

    private MimeMessage createEmail(BookSummary bookSummary) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailProperties.getRecipient());
            helper.setSubject(emailSubjectResolver.determineEmailSubject(bookSummary.getTitle()));
            helper.setText(emailBodyProvider.buildEmailBody(bookSummary), true);
            helper.setFrom(emailProperties.getFrom());

            return mail;
        } catch (MessagingException e) {
            throw new EmailPreparationException(bookSummary, e);
        }
    }
}
