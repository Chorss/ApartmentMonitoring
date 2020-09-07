package chorss.apartment.monitoring.measurement.repository;

import chorss.apartment.monitoring.measurement.Measurement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MeasurementRepository {

    Measurement findByUuid(UUID uuid);

    List<Measurement> findAllByCreatedBetweenAndDeviceUuid(LocalDateTime from, LocalDateTime to, UUID deviceID);

    List<Measurement> findAllByDeviceUuid(UUID deviceID);

    void save(Measurement measurement);

}