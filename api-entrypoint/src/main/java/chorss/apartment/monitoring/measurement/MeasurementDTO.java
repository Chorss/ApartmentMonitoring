package chorss.apartment.monitoring.measurement;

import java.math.BigDecimal;

public class MeasurementDTO {
    private BigDecimal temperature;
    private BigDecimal humidity;
    private String created;

    public BigDecimal getTemperature() {
        return temperature;
    }

    public MeasurementDTO setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
        return this;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public MeasurementDTO setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public MeasurementDTO setCreated(String created) {
        this.created = created;
        return this;
    }
}