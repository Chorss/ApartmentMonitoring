package chorss.apartment.monitoring.device;

import chorss.apartment.monitoring.DateTimeFormatterService;
import chorss.apartment.monitoring.devices.objects.Device;
import chorss.apartment.monitoring.devices.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
class DeviceController {

    private static final String DEVICES_INDEX_PAGE = "devices/index";
    private static final String DEVICES_REGISTER_PAGE = "devices/register";
    private static final String DEVICES_DEVICES_PAGE = "redirect:/devices";

    private final DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @ApiIgnore
    @GetMapping(value = "/devices")
    public String getDevices(Model model) {
        model.addAttribute("devices", getDeviceDTOList());
        return DEVICES_INDEX_PAGE;
    }

    @ApiIgnore
    @GetMapping(value = "/register/device")
    public String registerDevice() {
        return DEVICES_REGISTER_PAGE;
    }

    @ApiIgnore
    @PostMapping(value = "/register/device")
    public String registerDevice(@ModelAttribute("name") String name) {
        deviceService.register(name);
        return DEVICES_DEVICES_PAGE;
    }

    @ApiIgnore
    @GetMapping(value = "/remove/device/{uuid}")
    public String removeDevice(@PathVariable String uuid) {
        deviceService.remove(UUID.fromString(uuid));
        return DEVICES_DEVICES_PAGE;
    }

    private List<DeviceDTO> getDeviceDTOList() {
        return deviceService.getDevices()
                .stream()
                .filter(Objects::nonNull)
                .map(this::map)
                .collect(Collectors.toList());
    }

    private DeviceDTO map(Device device) {
        return new DeviceDTO()
                .setName(device.getName())
                .setUuid(String.valueOf(device.getUuid()))
                .setCreated(device.getCreated().format(DateTimeFormatterService.getDateTimeFormatter()));
    }
}