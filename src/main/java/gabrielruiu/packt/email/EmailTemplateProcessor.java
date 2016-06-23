package gabrielruiu.packt.email;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import gabrielruiu.packt.model.BookSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class EmailTemplateProcessor {

    @Autowired
    private Mustache.Compiler mustacheCompiler;

    public String generateEmailBody(String emailTemplate, BookSummary bookSummary) {
        Template template = mustacheCompiler.compile(emailTemplate);
        return template.execute(bookSummary);
    }
}
