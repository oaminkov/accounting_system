package ru.cgmd.accounting_system.controller;

import com.google.gson.Gson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cgmd.accounting_system.classes.Container;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.CountryService;
import ru.cgmd.accounting_system.service.ObservationDisciplineService;
import ru.cgmd.accounting_system.service.ObservationTypeService;
import ru.cgmd.accounting_system.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private final ObservationTypeService observationTypeService;
    private final ObservationDisciplineService observationDisciplineService;
    private final CountryService countryService;
    private final OrganizationService organizationService;

    public SearchController(
            ObservationTypeService observationTypeService,
            ObservationDisciplineService observationDisciplineService,
            CountryService countryService,
            OrganizationService organizationService
    ) {
        this.observationTypeService = observationTypeService;
        this.observationDisciplineService = observationDisciplineService;
        this.countryService = countryService;
        this.organizationService = organizationService;
    }

    //SEARCH START
    @GetMapping("/search")
    public String viewSearchPage(@AuthenticationPrincipal User user, Model model){
        /*List<ObservationDiscipline> observationDisciplines = observationDisciplineService.findByInformationProductsExists();
        List<ObservationType> observationTypes = observationTypeService.findByInformationProductsExists();
        List<Country> countries = countryService.findByInformationProductsExists();
        List<Organization> organizations = organizationService.findByInformationProductsExists();

        model.addAttribute("observationDisciplines", observationDisciplines);*/

        System.out.println(countryService.findByOrganizationsExists().toString());

        return "search";
    }

    /*@PostMapping("/search")
    public String searchResults(
            @RequestParam("choiceObservationDiscipline") ObservationDiscipline observationDiscipline,
            @RequestParam(value = "choiceObservationType", required = false, defaultValue = "") ObservationType observationType,
            @RequestParam(value = "choiceCountry", required = false, defaultValue = "") Country country,
            @RequestParam(value = "choiceOrganization", required = false, defaultValue = "") Organization organization,
            Model model) {

        List<InformationProduct> listInformationProducts;

        if (observationType == null) {
            listInformationProducts = informationProductRepository.findByObservationDiscipline(observationDiscipline);
        }
        else if (observationType != null && country == null)
        {
            listInformationProducts = informationProductRepository.findByObservationDisciplineAndObservationType(observationDiscipline, observationType);
        }
        else if (observationType != null && country != null && organization == null)
        {
            listInformationProducts = informationProductRepository.findByObservationDisciplineAndObservationTypeAndCountry(observationDiscipline, observationType, country);
        }
        else {
            listInformationProducts = informationProductRepository.findByObservationDisciplineAndObservationTypeAndCountryAndOrganization(observationDiscipline, observationType, country, organization);

            System.out.println(observationDiscipline.getName());
            System.out.println(observationType.getName());
            System.out.println(country);
            System.out.println(organization);
        }

        model.addAttribute("listInformationProducts", listInformationProducts);

        List<UploadedFile> uploadedFiles = uploadedFileRepository.findAll();
        model.addAttribute("uploadedFiles", uploadedFiles);

        return "view_information_resourses";
    }*/
    //SEARCH END

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
                    container.setName(item.getOrganization().getName());
                    containers.add(container);
                    break;
                }
            }
        }

        String json = new Gson().toJson(containers);
        return json;
    }*/
}
