package chorss.apartment.monitoring.service.measurement.service;

import chorss.apartment.monitoring.service.devices.repository.DeviceRepository;
import chorss.apartment.monitoring.service.measurement.entity.Mensuration;
import chorss.apartment.monitoring.service.measurement.objects.MensurationBO;
import chorss.apartment.monitoring.service.measurement.repository.MensurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
class MensurationServiceImpl implements MensurationService {

    private static final Logger LOG = LoggerFactory.getLogger(MensurationService.class);

    private final MensurationRepository mensurationRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    MensurationServiceImpl(MensurationRepository mensurationRepository, DeviceRepository deviceRepository) {
        this.mensurationRepository = mensurationRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Mensuration> getMeasurement(UUID deviceID) {
        LocalDateTime now = LocalDateTime.now();
        return mensurationRepository.findAllByCreatedBetweenAndDeviceUuid(now.minusDays(1), now, deviceID);
    }

    @Override
    public void addMensuration(MensurationBO bo) {
        try {
            Mensuration mensuration = new Mensuration();
            mensuration.setTemperature(bo.getTemperature());
            mensuration.setHumidity(bo.getHumidity());
            mensuration.setDevice(deviceRepository.findByUuid(bo.getDeviceId()));
            mensuration.setCreated(LocalDateTime.now());

            mensurationRepository.save(mensuration);
            LOG.info("Added mensuration");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}