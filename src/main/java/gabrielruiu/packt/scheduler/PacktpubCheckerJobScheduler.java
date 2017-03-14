package gabrielruiu.packt.scheduler;

import gabrielruiu.packt.SchedulerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
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

    @Autowired
    private SchedulerProperties schedulerProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        PacktpubCheckerJob packtpubCheckerJob = context.getBean(PacktpubCheckerJob.class);

        Integer interval = schedulerProperties.getInterval();
        TimeUnit timeUnit = schedulerProperties.getUnit();

        LOG.info(String.format("Starting %s with following settings: interval=[%d], unit=[%s]", PacktpubCheckerJob.class.getSimpleName(), interval, timeUnit));

        packtpubCheckerJob.start(interval, timeUnit);
    }
}
