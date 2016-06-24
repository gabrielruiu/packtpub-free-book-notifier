package gabrielruiu.packt.scheduler;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class EmailSubjectResolver {

    public String determineEmailSubject() {
        return "PacktPub Free ebook [" + getCurrentDate() + "]";
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
        return sdf.format(new Date());
    }
}
