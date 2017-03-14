package gabrielruiu.packt.exception;

import gabrielruiu.packt.BookSummary;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
public class EmailPreparationException extends RuntimeException {

    public EmailPreparationException(BookSummary bookSummary, Throwable e) {
        super(String.format("Error while trying to preparing the email with the following content: [%s]", bookSummary.toString()), e);
    }
}
