package chorss.apartment.monitoring.service.measurement.entity;

import chorss.apartment.monitoring.service.devices.entity.Device;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "measurement",
        indexes = {
                @Index(name = "measurement_index", columnList = "created")
        }
)
public class Mensuration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "deviceID")
    private Device device;

    @NotNull
    @Min(-100)
    @Max(100)
    private BigDecimal temperature;

    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal humidity;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}