package chorss.apartment.monitoring.devices.repository;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.transform.AccountTransform;
import chorss.apartment.monitoring.devices.jpa.JpaDeviceRepository;
import chorss.apartment.monitoring.devices.objects.Device;
import chorss.apartment.monitoring.devices.transform.DeviceTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceRepositoryImpl implements DeviceRepository {

    private final JpaDeviceRepository jpaDeviceRepository;
    private final DeviceTransform deviceTransform;
    private final AccountTransform accountTransform;

    @Autowired
    public DeviceRepositoryImpl(JpaDeviceRepository jpaDeviceRepository, DeviceTransform deviceTransform, AccountTransform accountTransform) {
        this.jpaDeviceRepository = jpaDeviceRepository;
        this.deviceTransform = deviceTransform;
        this.accountTransform = accountTransform;
    }

    @Override
    public Device findByName(String name) {
        return deviceTransform.map(jpaDeviceRepository.findByName(name));
    }

    @Override
    public Device findByUuid(UUID uuid) {
        return deviceTransform.map(jpaDeviceRepository.findByUuid(uuid));
    }

    @Override
    public List<Device> findAllByAccount(Account account) {
        AccountEntity accountEntity = accountTransform.map(account);

        return jpaDeviceRepository.findAllByAccount(accountEntity)
                .stream()
                .filter(Objects::nonNull)
                .map(deviceTransform::map)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Device device) {
        jpaDeviceRepository.save(deviceTransform.map(device));
    }

    @Override
    public void delete(Device device) {
        jpaDeviceRepository.delete(deviceTransform.map(device));
    }
}