package gabrielruiu.packt.email;

import gabrielruiu.packt.Main;
import gabrielruiu.packt.model.BookSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class EmailTemplateProviderTest {

    @Autowired
    EmailTemplateProvider emailTemplateProvider;

    @Test
    public void shouldCreateTheExpectedEmailBody() {
        String emailBody = emailTemplateProvider.generateEmailBody(emailTemplate(), getBookSummary());

        assertThat(emailBody, is(expectedEmailBody()));
    }

    private BookSummary getBookSummary() {
        return new BookSummary("Packtpub title", "Packtpub description", "Packtpub imageSrc", "Packtpub url");
    }

    private String emailTemplate() {
        return "<html>\n" +
                "<body>\n" +
                "<p>PacktPub free title of the day: {{title}}</p>\n" +
                "<p>{{description}}</p>\n" +
                "<p><img src=\"{{imageSrc}}\"/> </p>\n" +
                "<p><a href=\"{{url}}\">PacktPub</a> </p>\n" +
                "</body>\n" +
                "</html>";
    }

    private String expectedEmailBody() {
        return "<html>\n" +
                "<body>\n" +
                "<p>PacktPub free title of the day: Packtpub title</p>\n" +
                "<p>Packtpub description</p>\n" +
                "<p><img src=\"Packtpub imageSrc\"/> </p>\n" +
                "<p><a href=\"Packtpub url\">PacktPub</a> </p>\n" +
                "</body>\n" +
                "</html>";
    }
}