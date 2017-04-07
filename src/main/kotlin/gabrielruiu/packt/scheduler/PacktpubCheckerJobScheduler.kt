package gabrielruiu.packt.scheduler

import gabrielruiu.packt.SchedulerProperties
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
open class PacktpubCheckerJobScheduler
@Autowired constructor(
        val schedulerProperties: SchedulerProperties
) : ApplicationListener<ApplicationReadyEvent> {

    private val LOG = LoggerFactory.getLogger(PacktpubCheckerJobScheduler::class.java)

    override fun onApplicationEvent(event: ApplicationReadyEvent?) {
        val context = event!!.applicationContext
        val packtpubCheckerJob = context.getBean(PacktpubCheckerJob::class.java)

        val interval = schedulerProperties.interval
        val timeUnit = schedulerProperties.unit

        LOG.info(String.format("Starting %s with following settings: interval=[%d], unit=[%s]",
                PacktpubCheckerJob::class.java.simpleName, interval, timeUnit))

        packtpubCheckerJob.start(interval, timeUnit!!)
    }
}