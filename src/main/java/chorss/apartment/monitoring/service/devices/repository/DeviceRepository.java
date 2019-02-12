package chorss.apartment.monitoring.service.devices.repository;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import chorss.apartment.monitoring.service.devices.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findByName(String name);

    Device findByUuid(UUID id);

    List<Device> findAllByAccount(Account account);
}