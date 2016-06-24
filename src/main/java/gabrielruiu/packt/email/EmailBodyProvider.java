package gabrielruiu.packt.email;

import gabrielruiu.packt.model.BookSummary;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class EmailBodyProvider {

    @Autowired
    private EmailTemplateProcessor emailTemplateProcessor;

    @Autowired
    private ResourceLoader resourceLoader;

    public String buildEmailBody(BookSummary bookSummary) {
        return emailTemplateProcessor.generateEmailBody(getEmailTemplate(), bookSummary);
    }

    private String getEmailTemplate() {
        Resource emailTemplateFile = resourceLoader.getResource("classpath:templates/email.html");
        try {
            return IOUtils.toString(emailTemplateFile.getURI(), "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
