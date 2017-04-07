package gabrielruiu.packt

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.concurrent.TimeUnit

@ConfigurationProperties(prefix = "mail")
class EmailProperties {
    lateinit var recipient: String
    lateinit var from: String
}

@ConfigurationProperties(prefix = "mailgun")
class MailgunProperties {
    lateinit var key: String
    lateinit var url: String
    lateinit var domain: String
}

@ConfigurationProperties(prefix = "scheduler.time")
class SchedulerProperties{
    var interval: Int? = null
    lateinit var unit: TimeUnit
}