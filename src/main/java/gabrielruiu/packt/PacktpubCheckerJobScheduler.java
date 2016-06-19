package gabrielruiu.packt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Schedules {@link PacktpubCheckerJob} to scrape once per day the Packtpub website
 * and copy the title and the description of the free book for that day
 *
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class PacktpubCheckerJobScheduler implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PacktpubCheckerJobScheduler.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        ConfigurableEnvironment env = context.getEnvironment();
        PacktpubCheckerJob packtpubCheckerJob = context.getBean(PacktpubCheckerJob.class);

        Integer interval = env.getRequiredProperty("scheduler.time.interval", Integer.class);
        TimeUnit timeUnit = env.getRequiredProperty("scheduler.time.unit", TimeUnit.class);

        LOG.info(String.format("Starting %s with following settings: interval=[%d], unit=[%s]", PacktpubCheckerJob.class.getSimpleName(), interval, timeUnit));

        packtpubCheckerJob.start(interval, timeUnit);
    }
}
