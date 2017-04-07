package gabrielruiu.packt.scheduler

import gabrielruiu.packt.BookSummary
import gabrielruiu.packt.email.EmailSender
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class PacktpubChecker
@Autowired constructor(
        val emailSender: EmailSender
) {
    private val PACKTPUB_URL = "https://www.packtpub.com/packt/offers/free-learning"
    private val LOG = LoggerFactory.getLogger(PacktpubChecker::class.java)

    fun checkPacktpub() {
        val document = Jsoup.connect(PACKTPUB_URL).get()

        val bookSummaryElement = document.select(".dotd-main-book-summary").first()
        val bookTitleElement = bookSummaryElement.select(".dotd-title").first()
        val bookImageElement = document.select(".bookimage").first()

        val bookTitle = bookTitleElement.children()[0].text()
        val bookDescription = bookSummaryElement.children()[3].text()
        val bookImageSrc = prependHttpProtocol(bookImageElement.attr("src"))

        val bookSummary = BookSummary(bookTitle, bookDescription, bookImageSrc, PACKTPUB_URL)

        LOG.info("Retrieved following book summary=[{}]", bookSummary)
        emailSender.sendEmailWithBookSummary(bookSummary)
    }

    fun prependHttpProtocol(url: String): String {
        return "http://" + url
    }
}