package chorss.apartment.monitoring.measurement.transform;

import chorss.apartment.monitoring.devices.transform.DeviceTransform;
import chorss.apartment.monitoring.measurement.Measurement;
import chorss.apartment.monitoring.measurement.entity.MeasurementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class MeasurementTransform {

    private final DeviceTransform deviceTransform;

    @Autowired
    public MeasurementTransform(DeviceTransform deviceTransform) {
        this.deviceTransform = deviceTransform;
    }

    public MeasurementEntity map(Measurement measurement) {
        if (measurement == null) {
            return null;
        }

        MeasurementEntity entity = new MeasurementEntity();

        entity.setDevice(deviceTransform.map(measurement.getDevice()));
        entity.setHumidity(measurement.getHumidity());
        entity.setTemperature(measurement.getTemperature());
        entity.setCreated(measurement.getCreated());

        return entity;
    }

    public Measurement map(MeasurementEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Measurement()
                .setDevice(deviceTransform.map(entity.getDevice()))
                .setTemperature(entity.getTemperature())
                .setHumidity(entity.getHumidity())
                .setCreated(entity.getCreated());
    }
}