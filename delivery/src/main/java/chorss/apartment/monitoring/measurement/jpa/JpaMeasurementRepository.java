package chorss.apartment.monitoring.measurement.jpa;

import chorss.apartment.monitoring.measurement.entity.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaMeasurementRepository extends JpaRepository<MeasurementEntity, Long> {

    MeasurementEntity findByUuid(UUID uuid);

    List<MeasurementEntity> findAllByCreatedBetweenAndDeviceUuid(LocalDateTime from, LocalDateTime to, UUID deviceID);

    List<MeasurementEntity> findAllByDeviceUuid(UUID deviceID);
}