package chorss.apartment.monitoring.measurement.service;

import chorss.apartment.monitoring.measurement.Measurement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MeasurementService {

    void addMeasurement(BigDecimal temperature, BigDecimal humidity, UUID deviceId);

    List<Measurement> getMeasurementLast24Hours(UUID deviceID);

    List<Measurement> getMeasurementByDate(UUID deviceID, LocalDateTime startTime, LocalDateTime endTime);
}