package gabrielruiu.packt.email;

import gabrielruiu.packt.BookSummary;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
public interface EmailSender {

    void sendEmailWithBookSummary(BookSummary bookSummary);
}
