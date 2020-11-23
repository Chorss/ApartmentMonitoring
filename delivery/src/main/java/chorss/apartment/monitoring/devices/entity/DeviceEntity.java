package chorss.apartment.monitoring.devices.entity;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import chorss.apartment.monitoring.measurement.entity.MeasurementEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DEVICES")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "account")
    private AccountEntity account;

    @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MeasurementEntity> measurements;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime created;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}