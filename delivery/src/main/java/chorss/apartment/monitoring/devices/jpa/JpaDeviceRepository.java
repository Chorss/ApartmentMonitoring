package chorss.apartment.monitoring.devices.jpa;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import chorss.apartment.monitoring.devices.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaDeviceRepository extends JpaRepository<DeviceEntity, Long> {

    DeviceEntity findByName(String name);

    DeviceEntity findByUuid(UUID id);

    boolean existsByUuid(UUID id);

    List<DeviceEntity> findAllByAccount(AccountEntity account);
}