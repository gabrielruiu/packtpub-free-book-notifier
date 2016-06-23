package gabrielruiu.packt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@ConfigurationProperties(prefix = "mail")
public class EmailProperties {

    private String recipient;
    private String subject;
    private String from;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
