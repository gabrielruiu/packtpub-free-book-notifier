package gabrielruiu.packt.email.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import gabrielruiu.packt.email.EmailSender;
import gabrielruiu.packt.email.EmailTemplateProvider;
import gabrielruiu.packt.model.BookSummary;
import gabrielruiu.packt.properties.EmailProperties;
import gabrielruiu.packt.properties.MailgunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;


/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Profile("mailgun")
@Primary
@Component
public class MailgunRestEmailSender implements EmailSender {

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private MailgunProperties mailgunProperties;

    @Autowired
    private EmailTemplateProvider emailTemplateProvider;

    @Override
    public void sendEmailWithBookSummary(BookSummary bookSummary) {

        Client client = new Client();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgunProperties.getKey()));
        WebResource webResource = client.resource(buildMessagesResourceUrl());

        MultivaluedMapImpl form = new MultivaluedMapImpl();
        form.add("from", emailProperties.getFrom());
        form.add("to", emailProperties.getRecipient());
        form.add("subject", emailProperties.getSubject());
        form.add("html", emailTemplateProvider.generateEmailBody(bookSummary));
        form.add("o:tag", "packtpub-notifier");
        form.add("o:dkim", "yes");

        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
    }

    private String buildMessagesResourceUrl() {
        return mailgunProperties.getUrl() + mailgunProperties.getDomain() + "/messages";
    }
}
