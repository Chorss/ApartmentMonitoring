package chorss.apartment.monitoring.measurement.service;

import chorss.apartment.monitoring.devices.repository.DeviceRepository;
import chorss.apartment.monitoring.measurement.Measurement;
import chorss.apartment.monitoring.measurement.repository.MeasurementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
class MeasurementServiceImpl implements MeasurementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    MeasurementServiceImpl(MeasurementRepository measurementRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Measurement> getMeasurementLast24Hours(UUID deviceID) {
        LocalDateTime now = LocalDateTime.now();
        return measurementRepository.findAllByCreatedBetweenAndDeviceUuid(now.minusDays(1), now, deviceID);
    }

    @Override
    public List<Measurement> getMeasurementByDate(UUID deviceID, LocalDateTime startTime, LocalDateTime endTime) {
        return measurementRepository.findAllByCreatedBetweenAndDeviceUuid(startTime, endTime, deviceID);
    }

    @Override
    public void addMeasurement(BigDecimal temperature, BigDecimal humidity, UUID deviceId) {
        try {
            Measurement measurement = new Measurement()
                    .setCreated(LocalDateTime.now())
                    .setTemperature(temperature)
                    .setHumidity(humidity)
                    .setDevice(deviceRepository.findByUuid(deviceId));

            measurementRepository.save(measurement);
            LOGGER.info("Added mensuration");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}