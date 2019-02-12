package chorss.apartment.monitoring.service.measurement.service;

import chorss.apartment.monitoring.service.measurement.entity.Mensuration;
import chorss.apartment.monitoring.service.measurement.objects.MensurationBO;

import java.util.List;
import java.util.UUID;

public interface MensurationService {

    List<Mensuration> getMeasurement(UUID deviceID);

    void addMensuration(MensurationBO bo);
}