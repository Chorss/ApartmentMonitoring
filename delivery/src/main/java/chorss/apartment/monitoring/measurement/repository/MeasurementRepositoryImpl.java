package chorss.apartment.monitoring.measurement.repository;

import chorss.apartment.monitoring.measurement.Measurement;
import chorss.apartment.monitoring.measurement.jpa.JpaMeasurementRepository;
import chorss.apartment.monitoring.measurement.transform.MeasurementTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class MeasurementRepositoryImpl implements MeasurementRepository {

    private final JpaMeasurementRepository jpaMeasurementRepository;
    private final MeasurementTransform measurementTransform;

    @Autowired
    public MeasurementRepositoryImpl(JpaMeasurementRepository jpaMeasurementRepository, MeasurementTransform measurementTransform) {
        this.jpaMeasurementRepository = jpaMeasurementRepository;
        this.measurementTransform = measurementTransform;
    }

    @Override
    public Measurement findByUuid(UUID uuid) {
        return measurementTransform.map(jpaMeasurementRepository.findByUuid(uuid));
    }

    @Override
    public List<Measurement> findAllByCreatedBetweenAndDeviceUuid(LocalDateTime from, LocalDateTime to, UUID deviceID) {
        return jpaMeasurementRepository.findAllByCreatedBetweenAndDeviceUuid(from, to, deviceID)
                .stream()
                .filter(Objects::nonNull)
                .map(measurementTransform::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Measurement> findAllByDeviceUuid(UUID deviceID) {
        return jpaMeasurementRepository.findAllByDeviceUuid(deviceID)
                .stream()
                .filter(Objects::nonNull)
                .map(measurementTransform::map)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Measurement measurement) {
        jpaMeasurementRepository.save(measurementTransform.map(measurement));
    }
}