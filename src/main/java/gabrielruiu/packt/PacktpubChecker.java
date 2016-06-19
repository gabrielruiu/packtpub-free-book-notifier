package gabrielruiu.packt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class PacktpubChecker {

    private static final String PACKTPUB_URL = "https://www.packtpub.com/packt/offers/free-learning";

    private static final Logger LOG = LoggerFactory.getLogger(PacktpubChecker.class);

    public void checkPacktpub() {
        try {
            Document document = Jsoup.connect(PACKTPUB_URL).get();

            Element bookSummaryElement = document.select(".dotd-main-book-summary").first();
            Element bookTitleElement = bookSummaryElement.select(".dotd-title").first();
            Element bookImageElement = document.select(".bookimage").first();

            String bookTitle = bookTitleElement.children().get(0).text();
            String bookDescription = bookSummaryElement.children().get(3).text();
            String bookImageSrc = prependHttpProtocol(bookImageElement.attr("src"));

            BookSummary bookSummary = new BookSummary(bookTitle, bookDescription, bookImageSrc, PACKTPUB_URL);

            LOG.info("Retrieved following book summary=[{}]", bookSummary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String prependHttpProtocol(String url) {
        return "http:" + url;
    }
}
