package ru.cgmd.accounting_system.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ObservationTypeService observationTypeService;
    @Autowired
    private ObservationParameterService observationParameterService;

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

    //FOR SEARCH
    @GetMapping("/getObservationTypeList1")
    public @ResponseBody
    String ajaxResp1(@RequestParam("idObservationDiscipline") ObservationDiscipline observationDiscipline) {
        List<Container> containers = new ArrayList<Container>();

        List<ObservationType> observationTypes = observationTypeService.loadByObservationDiscipline(observationDiscipline);

        for (ObservationType observationType : observationTypes) {
            if (!observationType.getInformationResources().isEmpty()) {
                Container container = new Container();
                container.setId(observationType.getId());
                container.setName(observationType.getName());
                containers.add(container);
            }
        }

        String json = new Gson().toJson(containers);
        return json;
    }

    /*@GetMapping("/getCountryList1")
    public @ResponseBody
    String ajaxResp2(@RequestParam("idObservationType") ObservationType observationType) {
        List<Container> containers = new ArrayList<>();

        List<InformationProduct> informationProducts = informationProductRepository.findByObservationType(observationType);
        List<Country> countries = countryService.listAll();

        for (Country country : countries) {
            for (InformationProduct item : informationProducts) {
                if (item.getCountry().equals(country)) {
                    Container container = new Container();
                    container.setId(item.getCountry().getId());
                    container.setName(item.getCountry().getName());
                    containers.add(container);
                    break;
                }
            }
        }

        String json = new Gson().toJson(containers);
        return json;
    }*/

    /*@GetMapping("/getOrganizationList1")
    public @ResponseBody
    String ajaxResp3(@RequestParam("idCountry") Country country, @RequestParam("idObservationType") ObservationType observationType) {
        List<Container> containers = new ArrayList<>();

        List<InformationProduct> informationProducts = informationProductRepository.findByCountryAndObservationType(country, observationType);
        List<Organization> organizations = organizationService.listAll();

        for (Organization organization : organizations) {
            for (InformationProduct item : informationProducts) {
                if (item.getOrganization().equals(organization)) {
                    Container container = new Container();
                    container.setId(item.getOrganization().getId());
                    container.setName(item.getOrganization().getFullName());
                    containers.add(container);
                    break;
                }
            }
        }

        String json = new Gson().toJson(containers);
        return json;
    }*/
}
