package chorss.apartment.monitoring.controller;

import chorss.apartment.monitoring.service.devices.objects.DeviceDTO;
import chorss.apartment.monitoring.service.devices.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public String getDevices(Model model) {
        model.addAttribute("devices", deviceService.getDevices().stream().map(DeviceDTO::of).collect(Collectors.toList()));
        return "devices/index";
    }

    @RequestMapping(value = "/register/device", method = RequestMethod.GET)
    public String registerDevice() {
        return "devices/register";
    }

    @RequestMapping(value = "/register/device", method = RequestMethod.POST)
    public String registerDevice(
            @ModelAttribute("name") String name, Model model
    ) {
        deviceService.register(name);
        return "redirect:/devices";
    }

    @RequestMapping(value = "/remove/device/{uuid}", method = RequestMethod.GET)
    public String removeDevice(@PathVariable String uuid) {
        deviceService.remove(UUID.fromString(uuid));
        return "devices/index";
    }
}