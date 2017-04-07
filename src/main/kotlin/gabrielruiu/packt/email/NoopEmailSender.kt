package gabrielruiu.packt.email

import gabrielruiu.packt.BookSummary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("noop")
@Component
open class NoopEmailSender : EmailSender{

    override fun sendEmailWithBookSummary(bookSummary: BookSummary) {
    }
}