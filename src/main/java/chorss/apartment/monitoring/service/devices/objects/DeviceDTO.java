package chorss.apartment.monitoring.service.devices.objects;

import chorss.apartment.monitoring.service.devices.entity.Device;

public class DeviceDTO {

    private String uuid;
    private String name;
    private String created;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public static DeviceDTO of(Device device) {
        DeviceDTO dto = new DeviceDTO();
        dto.setUuid(device.getUuid().toString());
        dto.setName(device.getName());
        dto.setCreated(device.getCreated().toString());
        return dto;
    }
}