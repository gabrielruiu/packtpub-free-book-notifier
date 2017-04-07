package gabrielruiu.packt.email

import com.samskivert.mustache.Mustache
import gabrielruiu.packt.BookSummary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class EmailTemplateProcessor
@Autowired constructor(
        val mustacheCompiler: Mustache.Compiler
) {

    fun generateEmailBody(emailTemplate: String, bookSummary: BookSummary): String {
        val template = mustacheCompiler.compile(emailTemplate)
        return template.execute(bookSummary)
    }
}