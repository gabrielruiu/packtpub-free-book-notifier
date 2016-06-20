package gabrielruiu.packt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@ConfigurationProperties(prefix = "mail")
public class EmailProperties {

    private String recipient;
    private String subject;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
