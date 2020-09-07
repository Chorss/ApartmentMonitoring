package chorss.apartment.monitoring.measurement;

import chorss.apartment.monitoring.devices.objects.Device;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Measurement {
    private BigDecimal temperature;
    private BigDecimal humidity;
    private Device device;
    private LocalDateTime created;

    public BigDecimal getTemperature() {
        return temperature;
    }

    public Measurement setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
        return this;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public Measurement setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
        return this;
    }

    public Device getDevice() {
        return device;
    }

    public Measurement setDevice(Device device) {
        this.device = device;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Measurement setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}