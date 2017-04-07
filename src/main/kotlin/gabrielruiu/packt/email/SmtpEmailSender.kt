package gabrielruiu.packt.email

import gabrielruiu.packt.BookSummary
import gabrielruiu.packt.EmailPreparationException
import gabrielruiu.packt.EmailProperties
import gabrielruiu.packt.scheduler.EmailSubjectResolver
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Profile("smtp")
@Component
open class SmtpEmailSender(
    val javaMailSender: JavaMailSender,
    val emailBodyProvider: EmailBodyProvider,
    val emailSubjectResolver: EmailSubjectResolver,
    val emailProperties: EmailProperties
) : EmailSender {

    private val log = LoggerFactory.getLogger(SmtpEmailSender::class.java)

    override fun sendEmailWithBookSummary(bookSummary: BookSummary) {
        log.info(String.format("Sending email with following content: [%s]", bookSummary.toString()))
        val mail = createEmail(bookSummary)
        javaMailSender.send(mail)
        log.info("Email sent")
    }

    private fun createEmail(bookSummary: BookSummary): MimeMessage {
        try {
            val mail = javaMailSender.createMimeMessage()

            val helper = MimeMessageHelper(mail, true)
            helper.setTo(emailProperties.recipient!!)
            helper.setSubject(emailSubjectResolver.determineEmailSubject(bookSummary.title))
            helper.setText(emailBodyProvider.buildEmailBody(bookSummary), true)
            helper.setFrom(emailProperties.from!!)

            return mail
        } catch (e: MessagingException) {
            throw EmailPreparationException(bookSummary, e)
        }
    }
}