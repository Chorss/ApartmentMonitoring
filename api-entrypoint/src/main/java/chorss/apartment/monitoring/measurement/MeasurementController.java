package chorss.apartment.monitoring.measurement;

import chorss.apartment.monitoring.DateTimeFormatterService;
import chorss.apartment.monitoring.measurement.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
class MeasurementController {

    private static final String MEASUREMENT = "measurement";
    private static final String MEASUREMENT_PAGE = MEASUREMENT;
    private final MeasurementService mensurationService;

    @Autowired
    MeasurementController(MeasurementService mensurationService) {
        this.mensurationService = mensurationService;
    }

    @PostMapping(path = "api/v1/measurement")
    public ResponseEntity<HttpStatus> addMensuration(@RequestBody @Valid MeasurementBO mensurationBO) {
        mensurationService.addMeasurement(mensurationBO.getTemperature(), mensurationBO.getHumidity(), mensurationBO.getDeviceId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiIgnore
    @GetMapping(path = "measurement/{deviceUUID}")
    public String getMeasurement(@PathVariable String deviceUUID, Model model) {
        UUID uuid = UUID.fromString(deviceUUID);

        model.addAttribute(MEASUREMENT, getMeasurementDTOList(mensurationService.getMeasurementLast24Hours(uuid)));

        return MEASUREMENT_PAGE;
    }

    @ApiIgnore
    @GetMapping(path = "measurement/{deviceUUID}/{from}/{to}")
    public String getMeasurementByRangeDate(@PathVariable String deviceUUID, @PathVariable String from, @PathVariable String to, Model model) {
        UUID deviceID = UUID.fromString(deviceUUID);
        LocalDateTime startTime = LocalDateTime.parse(from);
        LocalDateTime endTime = LocalDateTime.parse(to);

        model.addAttribute(MEASUREMENT, getMeasurementDTOList(mensurationService.getMeasurementByDate(deviceID, startTime, endTime)));
        model.addAttribute("from", startTime.format(DateTimeFormatterService.getDateTimeFormatter()));
        model.addAttribute("to", endTime.format(DateTimeFormatterService.getDateTimeFormatter()));

        return MEASUREMENT_PAGE;
    }

    private List<MeasurementDTO> getMeasurementDTOList(List<Measurement> measurements) {
        return measurements
                .stream()
                .filter(Objects::nonNull)
                .map(this::mapDTO)
                .collect(Collectors.toList());
    }

    private MeasurementDTO mapDTO(Measurement measurement) {
        return new MeasurementDTO()
                .setHumidity(measurement.getHumidity())
                .setTemperature(measurement.getTemperature())
                .setCreated(measurement.getCreated().format(DateTimeFormatterService.getDateTimeFormatter()));
    }
}