package gabrielruiu.packt.email

import gabrielruiu.packt.BookSummary
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component

@Component
open class EmailBodyProvider
@Autowired constructor (
        val emailTemplateProcessor: EmailTemplateProcessor,
        val resourceLoader: ResourceLoader
) {

    fun buildEmailBody(bookSummary: BookSummary): String {
        return emailTemplateProcessor.generateEmailBody(getEmailTemplate(), bookSummary)
    }

    private fun getEmailTemplate(): String {
        val emailTemplateFile = resourceLoader.getResource("classpath:templates/email.html")
        return IOUtils.toString(emailTemplateFile.uri, "UTF8")
    }
}