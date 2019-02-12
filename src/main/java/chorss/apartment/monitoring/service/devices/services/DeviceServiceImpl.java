package chorss.apartment.monitoring.service.devices.services;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import chorss.apartment.monitoring.service.accounts.repository.AccountRepository;
import chorss.apartment.monitoring.service.devices.entity.Device;
import chorss.apartment.monitoring.service.devices.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
class DeviceServiceImpl implements DeviceService {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceService.class);

    private final DeviceRepository deviceRepository;
    private final AccountRepository accountRepository;

    @Autowired
    DeviceServiceImpl(DeviceRepository deviceRepository, AccountRepository accountRepository) {
        this.deviceRepository = deviceRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String name) {
        try {
            Device device = new Device();
            device.setName(name);
            device.setCreated(LocalDateTime.now());
            device.setAccount(getAccount());
            deviceRepository.save(device);
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    @Override
    public void remove(UUID uuid) {
        Device device = deviceRepository.findByUuid(uuid);

        if (device.getAccount().getEmail().equals(getAccount().getEmail())) {
            deviceRepository.delete(device);
        }
    }

    @Override
    public List<Device> getDevices() {
        return deviceRepository.findAllByAccount(getAccount());
    }

    private Account getAccount() {
        return accountRepository.findByEmail(getUsername());
    }

    private String getUsername() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}