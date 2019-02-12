package chorss.apartment.monitoring.service.measurement.repository;

import chorss.apartment.monitoring.service.measurement.entity.Mensuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MensurationRepository extends JpaRepository<Mensuration, Long> {

    Mensuration findByUuid(UUID uuid);

    List<Mensuration> findAllByCreatedBetweenAndDeviceUuid(LocalDateTime from, LocalDateTime to, UUID deviceID);

    List<Mensuration> findAllByDeviceUuid(UUID deviceID);

}