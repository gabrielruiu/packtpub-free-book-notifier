package gabrielruiu.packt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(value = *arrayOf(EmailProperties::class, MailgunProperties::class, SchedulerProperties::class))
open class Main {

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Main::class.java, *args)
            sleepIndefinitely()
        }

        @Throws(InterruptedException::class)
        private fun sleepIndefinitely() {
            while (true) {
                Thread.sleep(java.lang.Long.MAX_VALUE)
            }
        }
    }
}