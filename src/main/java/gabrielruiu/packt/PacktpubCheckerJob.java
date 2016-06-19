package gabrielruiu.packt;

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

    public void start(Integer duration, TimeUnit timeUnit) {
        Observable.interval(duration, timeUnit)
                  .doOnNext(interval -> packtpubChecker.checkPacktPub());
    }
}
