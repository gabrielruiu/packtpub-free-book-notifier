package gabrielruiu.packt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@SpringBootApplication
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
