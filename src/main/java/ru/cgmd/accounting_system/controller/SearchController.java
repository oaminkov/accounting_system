package ru.cgmd.accounting_system.controller;

import com.google.gson.Gson;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam(required = false) ObservationDiscipline observationDiscipline,
            @RequestParam(required = false) ObservationType observationType,
            Model model
    ) {
        if (inventoryNumber.isEmpty()) inventoryNumber = null;
        if (dateObservationStart.isEmpty()) dateObservationStart = null;
        if (dateObservationEnd.isEmpty()) dateObservationEnd = null;

        InformationResource informationResource = new InformationResource();
        informationResource.setInventoryNumber(inventoryNumber);
        informationResource.setDateObservationStart(dateObservationStart);
        informationResource.setDateObservationEnd(dateObservationEnd);
        informationResource.setResourceType(resourceType);
        informationResource.setLanguage(language);
        informationResource.setCountry(country);
        informationResource.setMainOrganization(mainOrganization);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<InformationResource> example = Example.of(informationResource, matcher);

        /*Set<ObservationDiscipline> observationDisciplines = new HashSet<>(Arrays.asList(observationDiscipline));
        Set<ObservationType> observationTypes = new HashSet<>(Arrays.asList(observationType));
        informationResource.setObservationDisciplines(observationDisciplines);*/

        List<InformationResource> informationResources = informationResourceRepository.findAll(example);

        System.out.println("<INPUT>\n" + informationResource + "</INPUT>\n");
        System.out.println("<OUTPUT1>\n" + informationResources + "</OUTPUT1>\n");

        if (observationDiscipline != null) {
            List<InformationResource> informationResourcesTemp = new ArrayList<>();

            if (observationType != null) {
                for (InformationResource resource : informationResources) {
                    if (resource.getObservationDisciplines().contains(observationDiscipline) && resource.getObservationTypes().contains(observationType)) {
                        informationResourcesTemp.add(resource);
                    }
                }
            }
            else {
                for (InformationResource resource : informationResources) {
                    if (resource.getObservationDisciplines().contains(observationDiscipline)) {
                        informationResourcesTemp.add(resource);
                    }
                }
            }
            informationResources = informationResourcesTemp;

            System.out.println("<OUTPUT2>\n" + informationResources + "</OUTPUT2>\n");
        }

        model.addAttribute("informationResources", informationResources);
        return "view_information_resources";
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
