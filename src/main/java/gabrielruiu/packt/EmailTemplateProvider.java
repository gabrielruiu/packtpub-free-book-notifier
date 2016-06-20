package gabrielruiu.packt;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class EmailTemplateProvider {

    @Autowired
    private Mustache.Compiler mustacheCompiler;

    public String generateEmailBody(BookSummary bookSummary) {
        Template template = mustacheCompiler.compile("email");
        return template.execute(bookSummary);
    }
}
