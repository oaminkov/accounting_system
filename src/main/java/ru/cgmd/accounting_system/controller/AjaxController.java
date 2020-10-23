package ru.cgmd.accounting_system.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cgmd.accounting_system.classes.Container;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;
import ru.cgmd.accounting_system.domain.ObservationParameter;
import ru.cgmd.accounting_system.domain.ObservationType;
import ru.cgmd.accounting_system.service.ObservationParameterService;
import ru.cgmd.accounting_system.service.ObservationTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    private final ObservationTypeService observationTypeService;
    private final ObservationParameterService observationParameterService;

    public AjaxController(ObservationTypeService observationTypeService, ObservationParameterService observationParameterService) {
        this.observationTypeService = observationTypeService;
        this.observationParameterService = observationParameterService;
    }

    @GetMapping("/getObservationTypeList")
    public @ResponseBody
    String ajaxRespObservationTypes(@RequestParam("id") ObservationDiscipline observationDiscipline) {
        List<Container> containers = new ArrayList<Container>();

        List<ObservationType> observationTypes = observationTypeService.loadByObservationDiscipline(observationDiscipline);

        for (ObservationType observationType : observationTypes) {
            Container container = new Container();
            container.setId(observationType.getId());
            container.setName(observationType.getName());
            containers.add(container);
        }

        String json = new Gson().toJson(containers);
        return json;
    }

    @GetMapping("/getObservationParameterList")
    public @ResponseBody
    String ajaxRespObservationParameters(@RequestParam("id") ObservationType observationType) {
        List<Container> containers = new ArrayList<Container>();

        List<ObservationParameter> observationParameters = observationParameterService.loadByObservationType(observationType);

        for (ObservationParameter observationParameter : observationParameters) {
            Container container = new Container();
            container.setId(observationParameter.getId());
            container.setName(observationParameter.getName());
            containers.add(container);
        }

        String json = new Gson().toJson(containers);
        return json;
    }
}
