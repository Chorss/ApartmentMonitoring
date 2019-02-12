package chorss.apartment.monitoring.controller;

import chorss.apartment.monitoring.service.measurement.objects.MensurationBO;
import chorss.apartment.monitoring.service.measurement.service.MensurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@Controller
class MeasurementController {

    private final MensurationService mensurationService;

    @Autowired
    MeasurementController(MensurationService mensurationService) {
        this.mensurationService = mensurationService;
    }

    @RequestMapping(path = "api/v1/measurement", method = RequestMethod.POST)
    public ResponseEntity addMensuration(
            @Valid @RequestBody MensurationBO mensurationBO
    ) {
        mensurationService.addMensuration(mensurationBO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "measurement/{deviceUUID}", method = RequestMethod.GET)
    public String getMeasurement(
            @PathVariable String deviceUUID, Model model
    ) {
        model.addAttribute("measurement", mensurationService.getMeasurement(UUID.fromString(deviceUUID)));
        return "mensuration";
    }
}