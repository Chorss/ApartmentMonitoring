package chorss.apartment.monitoring.devices.services;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.service.LoggedAccountService;
import chorss.apartment.monitoring.devices.objects.Device;
import chorss.apartment.monitoring.devices.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private LoggedAccountService loggedAccountService;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void shouldReturnDevicesAssignedToLoggedAccount() {
        // Given
        Account testAccount = new Account()
                .setUuid(UUID.randomUUID())
                .setName("test account")
                .setEmail("minitest@test.test")
                .setRoles(new ArrayList<>());

        Device device = createDevice(UUID.randomUUID(), testAccount, "name 1", LocalDateTime.now());
        Device deviceMiki = createDevice(UUID.randomUUID(), testAccount, "Miki", LocalDateTime.now());
        Device deviceFlat = createDevice(UUID.randomUUID(), testAccount, "My flat", LocalDateTime.now());

        List<Device> devices = new ArrayList<>();
        devices.add(device);
        devices.add(deviceMiki);
        devices.add(deviceFlat);

        when(loggedAccountService.getAccount()).thenReturn(testAccount);
        when(deviceRepository.findAllByAccount(testAccount)).thenReturn(devices);

        // When
        List<Device> returnedDevices = deviceService.getDevices();

        // Then
        assertAll("deviced",
                () -> assertEquals(3, returnedDevices.size()),
                () -> {
                    Device deviceR1 = returnedDevices.get(0);
                    assertNotNull(deviceR1);

                    assertAll("Check device",
                            () -> assertEquals(device.getUuid(), deviceR1.getUuid()),
                            () -> assertEquals(device.getName(), deviceR1.getName()),
                            () -> assertEquals(device.getAccount(), deviceR1.getAccount()),
                            () -> assertEquals(device.getCreated(), deviceR1.getCreated())
                    );
                },
                () -> {
                    Device deviceR2 = returnedDevices.get(1);
                    assertNotNull(deviceR2);

                    assertAll("Check device Miki",
                            () -> assertEquals(deviceMiki.getUuid(), deviceR2.getUuid()),
                            () -> assertEquals(deviceMiki.getName(), deviceR2.getName()),
                            () -> assertEquals(deviceMiki.getAccount(), deviceR2.getAccount()),
                            () -> assertEquals(deviceMiki.getCreated(), deviceR2.getCreated())
                    );
                },
                () -> {
                    Device deviceR3 = returnedDevices.get(2);
                    assertNotNull(deviceR3);

                    assertAll("Check device Flat",
                            () -> assertEquals(deviceFlat.getUuid(), deviceR3.getUuid()),
                            () -> assertEquals(deviceFlat.getName(), deviceR3.getName()),
                            () -> assertEquals(deviceFlat.getAccount(), deviceR3.getAccount()),
                            () -> assertEquals(deviceFlat.getCreated(), deviceR3.getCreated())
                    );
                }
        );
    }

    @Test
    void shouldReturnEmptyListDevicesAssignedToNotLoggedAccount() {
        // Given
        when(loggedAccountService.getAccount()).thenReturn(null);

        // When
        List<Device> returnedDevices = deviceService.getDevices();

        assertEquals(0, returnedDevices.size());
    }

    private Device createDevice(UUID uuid, Account account, String deviceName, LocalDateTime created) {
        return new Device()
                .setUuid(uuid)
                .setAccount(account)
                .setName(deviceName)
                .setCreated(created);
    }
}