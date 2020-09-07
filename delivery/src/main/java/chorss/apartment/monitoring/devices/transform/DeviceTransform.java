package chorss.apartment.monitoring.devices.transform;

import chorss.apartment.monitoring.account.transform.AccountTransform;
import chorss.apartment.monitoring.devices.entity.DeviceEntity;
import chorss.apartment.monitoring.devices.jpa.JpaDeviceRepository;
import chorss.apartment.monitoring.devices.objects.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class DeviceTransform {

    private final JpaDeviceRepository jpaDeviceRepository;
    private final AccountTransform accountTransform;

    @Autowired
    public DeviceTransform(JpaDeviceRepository jpaDeviceRepository, AccountTransform accountTransform) {
        this.jpaDeviceRepository = jpaDeviceRepository;
        this.accountTransform = accountTransform;
    }

    public DeviceEntity map(Device device) {
        DeviceEntity entity;

        if (device.getUuid() != null && jpaDeviceRepository.existsByUuid(device.getUuid())) {
            entity = jpaDeviceRepository.findByUuid(device.getUuid());
        } else {
            entity = new DeviceEntity();
        }

        entity.setUuid(device.getUuid());
        entity.setName(device.getName());
        entity.setCreated(device.getCreated());
        entity.setAccount(accountTransform.map(device.getAccount()));
        return entity;
    }

    public Device map(DeviceEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Device()
                .setUuid(entity.getUuid())
                .setName(entity.getName())
                .setAccount(accountTransform.map(entity.getAccount()))
                .setCreated(entity.getCreated());
    }
}