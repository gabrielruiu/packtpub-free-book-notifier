package gabrielruiu.packt.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@Component
public class PacktpubCheckerJob {

    private PacktpubChecker packtpubChecker;

    @Autowired
    public PacktpubCheckerJob(PacktpubChecker packtpubChecker) {
        this.packtpubChecker = packtpubChecker;
    }

    /**
     * Runs {@link PacktpubChecker#checkPacktpub()} each time the interval Observable emits a new item
     */
    public void start(Integer interval, TimeUnit timeUnit) {
        Observable.interval(interval, timeUnit)
                  .doOnNext(counter -> packtpubChecker.checkPacktpub())
                  .subscribe();
    }
}
