package gabrielruiu.packt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class PacktpubChecker {

    private static final Logger LOG = LoggerFactory.getLogger(PacktpubChecker.class);

    public void checkPacktPub() {
        LOG.info("Retrieving title and description of free ebook on PacktPub");
    }
}
