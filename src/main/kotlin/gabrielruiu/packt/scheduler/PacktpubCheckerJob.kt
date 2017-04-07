package gabrielruiu.packt.scheduler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import rx.Observable
import java.util.concurrent.TimeUnit

@Component
open class PacktpubCheckerJob
@Autowired constructor(
        val packtpubChecker: PacktpubChecker
) {

    /**
     * Runs [PacktpubChecker.checkPacktpub] each time the interval Observable emits a new item
     */
    fun start(interval: Int?, timeUnit: TimeUnit) {
        Observable.interval(interval!!.toLong(), timeUnit)
                .doOnNext { _ -> packtpubChecker.checkPacktpub() }
                .subscribe()
    }
}