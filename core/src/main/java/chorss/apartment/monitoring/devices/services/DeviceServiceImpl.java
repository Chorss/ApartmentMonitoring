package chorss.apartment.monitoring.devices.services;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.service.LoggedAccountService;
import chorss.apartment.monitoring.devices.objects.Device;
import chorss.apartment.monitoring.devices.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
class DeviceServiceImpl implements DeviceService {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;
    private final LoggedAccountService loggedAccountService;

    @Autowired
    DeviceServiceImpl(DeviceRepository deviceRepository, LoggedAccountService loggedAccountService) {
        this.deviceRepository = deviceRepository;
        this.loggedAccountService = loggedAccountService;
    }

    @Override
    public void register(String name) {
        try {
            Device device = new Device();
            device.setName(name);
            device.setCreated(LocalDateTime.now());
            device.setAccount(loggedAccountService.getAccount());

            deviceRepository.save(device);
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    @Override
    public void remove(UUID uuid) {
        Device device = deviceRepository.findByUuid(uuid);
        String email = loggedAccountService.getAccount().getEmail();

        if (isLoggedAccountEqualAccountFromDevice(device, email)) {
            LOG.debug("Remove Device device:{}", device);
            deviceRepository.delete(device);
        } else {
            LOG.error("Attempted device deletion by an unauthorized user. email:{}, device:{}", email, device);
        }
    }

    @Override
    public List<Device> getDevices() {
        Account loggedAccount = loggedAccountService.getAccount();
        return deviceRepository.findAllByAccount(loggedAccount);
    }

    private boolean isLoggedAccountEqualAccountFromDevice(Device device, String email) {
        return device.getAccount() != null && device.getAccount().getEmail().equalsIgnoreCase(email);
    }
}