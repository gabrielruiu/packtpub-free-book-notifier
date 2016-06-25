package gabrielruiu.packt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
@ConfigurationProperties(prefix = "scheduler.time")
public class SchedulerProperties {

    private Integer interval;
    private TimeUnit unit;
    private String start;

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
