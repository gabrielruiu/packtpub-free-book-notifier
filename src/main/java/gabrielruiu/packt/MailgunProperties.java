package gabrielruiu.packt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@ConfigurationProperties(prefix = "mailgun")
public class MailgunProperties {

    private String key;
    private String url;
    private String domain;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
