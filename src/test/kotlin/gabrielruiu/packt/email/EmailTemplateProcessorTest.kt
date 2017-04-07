package gabrielruiu.packt.email

import gabrielruiu.packt.BookSummary
import gabrielruiu.packt.Main
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(Main::class)
@IntegrationTest("spring.profiles.active=noop")
class EmailTemplateProcessorTest {

    @Autowired
    lateinit var emailTemplateProcessor: EmailTemplateProcessor

    @Test
    fun shouldCreateTheExpectedEmailBody() {
        val emailBody = emailTemplateProcessor.generateEmailBody(emailTemplate(), getBookSummary())

        assertThat(emailBody, `is`(expectedEmailBody()))
    }

    private fun getBookSummary(): BookSummary {
        return BookSummary("Packtpub title", "Packtpub description", "Packtpub imageSrc", "Packtpub url")
    }

    private fun emailTemplate(): String {
        return "<html>\n" +
                "<body>\n" +
                "<p>PacktPub free title of the day: {{title}}</p>\n" +
                "<p>{{description}}</p>\n" +
                "<p><img src=\"{{imageSrc}}\"/> </p>\n" +
                "<p><a href=\"{{url}}\">PacktPub</a> </p>\n" +
                "</body>\n" +
                "</html>"
    }

    private fun expectedEmailBody(): String {
        return "<html>\n" +
                "<body>\n" +
                "<p>PacktPub free title of the day: Packtpub title</p>\n" +
                "<p>Packtpub description</p>\n" +
                "<p><img src=\"Packtpub imageSrc\"/> </p>\n" +
                "<p><a href=\"Packtpub url\">PacktPub</a> </p>\n" +
                "</body>\n" +
                "</html>"
    }
}