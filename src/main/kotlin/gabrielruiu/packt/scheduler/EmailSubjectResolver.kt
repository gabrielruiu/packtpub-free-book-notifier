package gabrielruiu.packt.scheduler

import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
open class EmailSubjectResolver {

    fun determineEmailSubject(bookTitle: String): String {
        return bookTitle + '[' + getCurrentDate() + ']'
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return sdf.format(Date())
    }
}
