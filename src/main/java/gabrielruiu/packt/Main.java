package gabrielruiu.packt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {EmailProperties.class, MailgunProperties.class, SchedulerProperties.class})
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        sleepIndefinitely();
    }

    private static void sleepIndefinitely() throws InterruptedException {
        while (true) {
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}
