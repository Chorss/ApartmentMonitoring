package chorss.apartment.monitoring.service.measurement.objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class MensurationBO {

    @NotNull
    @Min(-100)
    @Max(100)
    private BigDecimal temperature;

    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal humidity;

    @NotNull
    private UUID deviceId;

    public MensurationBO() {
    }

    public MensurationBO(BigDecimal temperature, BigDecimal humidity, UUID deviceId) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.deviceId = deviceId;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }
}