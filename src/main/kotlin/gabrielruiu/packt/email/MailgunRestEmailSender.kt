package gabrielruiu.packt.email

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter
import com.sun.jersey.core.util.MultivaluedMapImpl
import gabrielruiu.packt.BookSummary
import gabrielruiu.packt.EmailProperties
import gabrielruiu.packt.MailgunProperties
import gabrielruiu.packt.scheduler.EmailSubjectResolver
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.ws.rs.core.MediaType

@Profile("mailgun")
@Primary
@Component
open class MailgunRestEmailSender(
        val emailProperties: EmailProperties,
        val mailgunProperties: MailgunProperties,
        val emailBodyProvider: EmailBodyProvider,
        val emailSubjectResolver: EmailSubjectResolver
) : EmailSender {

    private val log = LoggerFactory.getLogger(MailgunRestEmailSender::class.java)

    override fun sendEmailWithBookSummary(bookSummary: BookSummary) {
        log.info(String.format("Sending email with following content: [%s]", bookSummary.toString()))
        val client = Client()
        client.addFilter(HTTPBasicAuthFilter("api", mailgunProperties.key!!))
        val webResource = client.resource(buildMessagesResourceUrl())

        val form = MultivaluedMapImpl()
        form.add("from", emailProperties.from)
        form.add("to", emailProperties.recipient)
        form.add("subject", emailSubjectResolver.determineEmailSubject(bookSummary.title))
        form.add("html", emailBodyProvider.buildEmailBody(bookSummary))
        form.add("o:tag", "packtpub-notifier")
        form.add("o:dkim", "yes")

        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse::class.java, form)
        log.info("Email sent")
    }

    private fun buildMessagesResourceUrl(): String {
        return mailgunProperties.url + mailgunProperties.domain + "/messages"
    }
}