package chorss.apartment.monitoring.devices.repository;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.devices.objects.Device;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository {

    Device findByName(String name);

    Device findByUuid(UUID id);

    List<Device> findAllByAccount(Account account);

    void save(Device device);

    void delete(Device device);
}