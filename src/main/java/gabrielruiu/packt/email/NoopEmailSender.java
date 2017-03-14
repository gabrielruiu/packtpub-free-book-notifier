package gabrielruiu.packt.email;

import gabrielruiu.packt.BookSummary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Profile("noop")
@Component
public class NoopEmailSender implements EmailSender {

    @Override
    public void sendEmailWithBookSummary(BookSummary bookSummary) {

    }
}
