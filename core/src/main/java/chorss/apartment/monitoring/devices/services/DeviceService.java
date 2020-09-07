package chorss.apartment.monitoring.devices.services;

import chorss.apartment.monitoring.devices.objects.Device;

import java.util.List;
import java.util.UUID;

public interface DeviceService {

    void register(String name);

    List<Device> getDevices();

    void remove(UUID uuid);
}