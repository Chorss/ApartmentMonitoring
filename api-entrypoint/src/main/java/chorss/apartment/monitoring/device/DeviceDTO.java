package chorss.apartment.monitoring.device;

class DeviceDTO {
    private String uuid;
    private String name;
    private String created;

    public String getUuid() {
        return uuid;
    }

    public DeviceDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeviceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public DeviceDTO setCreated(String created) {
        this.created = created;
        return this;
    }
}