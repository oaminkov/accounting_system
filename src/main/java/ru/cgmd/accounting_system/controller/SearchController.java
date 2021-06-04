package ru.cgmd.accounting_system.controller;

import com.google.gson.Gson;
import org.springframework.data.domain.Example;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cgmd.accounting_system.classes.Container;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repo.InformationResourceRepository;
import ru.cgmd.accounting_system.repo.UploadedFileRepository;
import ru.cgmd.accounting_system.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {
    private final InformationResourceService informationResourceService;
    private final ResourceTypeService resourceTypeService;
    private final LanguageService languageService;
    private final RelatedProjectService relatedProjectService;
    private final ObservationMethodService observationMethodService;
    private final CountryService countryService;
    private final OrganizationService organizationService;

    private final ObservationDisciplineService observationDisciplineService;
    private final ObservationTypeService observationTypeService;
    private final ObservationParameterService observationParameterService;

    private final ObservationScopeService observationScopeService;
    private final ObservationTerritoryService observationTerritoryService;
    private final UploadedFileRepository uploadedFileRepository;
    private final InformationResourceRepository informationResourceRepository;

    public SearchController(
            InformationResourceService informationResourceService,
            ResourceTypeService resourceTypeService,
            LanguageService languageService,
            RelatedProjectService relatedProjectService,
            ObservationMethodService observationMethodService,
            CountryService countryService,
            OrganizationService organizationService,
            ObservationDisciplineService observationDisciplineService,
            ObservationTypeService observationTypeService,
            ObservationParameterService observationParameterService,
            ObservationScopeService observationScopeService,
            ObservationTerritoryService observationTerritoryService,
            UploadedFileRepository uploadedFileRepository,
            InformationResourceRepository informationResourceRepository
    ) {
        this.informationResourceService = informationResourceService;
        this.resourceTypeService = resourceTypeService;
        this.languageService = languageService;
        this.relatedProjectService = relatedProjectService;
        this.observationMethodService = observationMethodService;
        this.countryService = countryService;
        this.organizationService = organizationService;

        this.observationDisciplineService = observationDisciplineService;
        this.observationTypeService = observationTypeService;
        this.observationParameterService = observationParameterService;

        this.observationScopeService = observationScopeService;
        this.observationTerritoryService = observationTerritoryService;
        this.uploadedFileRepository = uploadedFileRepository;

        this.informationResourceRepository = informationResourceRepository;
    }

    public void selectDataFromDbToModel(Model model) {
        List<ResourceType> resourceTypes = resourceTypeService.listAll();
        List<Language> languages = languageService.listAll();
        List<RelatedProject> relatedProjects = relatedProjectService.listAll();
        List<ObservationMethod> observationMethods = observationMethodService.listAll();
        List<Country> countries = countryService.listAll();
        List<Organization> organizations = organizationService.listAll();
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        List<ObservationScope> observationScopes = observationScopeService.listAll();
        List<ObservationTerritory> observationTerritories = observationTerritoryService.listAll();

        model.addAttribute("resourceTypes", resourceTypes);
        model.addAttribute("languages", languages);
        model.addAttribute("relatedProjects", relatedProjects);
        model.addAttribute("observationMethods", observationMethods);
        model.addAttribute("countries", countries);
        model.addAttribute("organizations", organizations);
        model.addAttribute("observationDisciplines", observationDisciplines);
        model.addAttribute("observationScopes", observationScopes);
        model.addAttribute("observationTerritories", observationTerritories);
    }

    @GetMapping
    public String viewSearchPage(@AuthenticationPrincipal User user, Model model){
        selectDataFromDbToModel(model);

        return "search";
    }

    @PostMapping
    public String searchResults(
            @RequestParam(required = false) String inventoryNumber,
            @RequestParam(required = false) String dateObservationStart,
            @RequestParam(required = false) String dateObservationEnd,
            @RequestParam(required = false) ResourceType resourceType,
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) Country country,
            @RequestParam(required = false) Organization mainOrganization,
            Model model
    ) {
        /*if (resourceType == null) {
            System.out.println(resourceType);
        }
        else System.out.println(resourceType.getName());

        if (language == null) {
            System.out.println(language);
        }
        else System.out.println(language.getName());

        if (country == null) {
            System.out.println(country);
        }
        else System.out.println(country.getName());

        if (mainOrganization == null) {
            System.out.println(mainOrganization);
        }
        else System.out.println(mainOrganization.getName());*/

        /*System.out.println(inventoryNumber);
        System.out.println("<"+ dateObservationStart + "> <" + dateObservationEnd + ">");*/

        InformationResource resource = new InformationResource();
        resource.setCountry(country);
        resource.setLanguage(language);


        System.out.println("++\n" + resource + "++\n");

        List<InformationResource> informationResources = informationResourceRepository.findAll(Example.of(resource));

        for (InformationResource informationResource : informationResources) {
            System.out.println(informationResource.toString());
        }

        model.addAttribute("informationResources", informationResources);
        return "view_information_resources";

        //return "redirect:/search";
    }
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
