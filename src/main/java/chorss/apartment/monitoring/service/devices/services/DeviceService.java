package chorss.apartment.monitoring.service.devices.services;

import chorss.apartment.monitoring.service.devices.entity.Device;
import chorss.apartment.monitoring.service.devices.objects.DeviceDTO;

import java.util.List;
import java.util.UUID;

public interface DeviceService {

    void register(String name);

    List<Device> getDevices();

    void remove(UUID uuid);
}