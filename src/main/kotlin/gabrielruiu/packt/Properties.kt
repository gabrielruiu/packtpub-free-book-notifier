package gabrielruiu.packt

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.concurrent.TimeUnit

@ConfigurationProperties(prefix = "mail")
class EmailProperties {
    var recipient: String? = null
    var from: String? = null
}

@ConfigurationProperties(prefix = "mailgun")
class MailgunProperties {
    var key: String? = null
    var url: String? = null
    var domain: String? = null
}

@ConfigurationProperties(prefix = "scheduler.time")
class SchedulerProperties{
    var interval: Int? = null
    var unit: TimeUnit? = null
}