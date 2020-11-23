package chorss.apartment.monitoring.measurement.entity;

import chorss.apartment.monitoring.devices.entity.DeviceEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "MEASUREMENT",
        indexes = {
                @Index(name = "MEASUREMENT_DEVICE_ID_IDX", columnList = "DEVICE_ID"),
                @Index(name = "MEASUREMENT_CREATED_IDX", columnList = "CREATED")
        }
)
public class MeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    private DeviceEntity device;

    @NotNull
    @Min(-100)
    @Max(100)
    @Column(nullable = false)
    private BigDecimal temperature;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private BigDecimal humidity;

    @Column(nullable = false)
    private LocalDateTime created;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
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