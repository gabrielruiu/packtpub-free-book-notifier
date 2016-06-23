package gabrielruiu.packt.email.smtp;

import gabrielruiu.packt.email.EmailSender;
import gabrielruiu.packt.email.EmailTemplateProvider;
import gabrielruiu.packt.model.BookSummary;
import gabrielruiu.packt.properties.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Profile("smtp")
@Primary
@Component
public class SmtpEmailSender implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailTemplateProvider emailTemplateProvider;

    @Autowired
    private EmailProperties emailProperties;

    public void sendEmailWithBookSummary(BookSummary bookSummary) {
        SimpleMailMessage mail = createEmail(bookSummary);
        javaMailSender.send(mail);
    }

    private SimpleMailMessage createEmail(BookSummary bookSummary) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailProperties.getRecipient());
        mail.setSubject(emailProperties.getSubject());
        mail.setText(emailTemplateProvider.generateEmailBody("", bookSummary));
        return mail;
    }
}
