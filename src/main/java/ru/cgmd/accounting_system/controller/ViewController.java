package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.*;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private RelatedProjectService relatedProjectService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ObservationMethodService observationMethodService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationTypeService observationTypeService;
    @Autowired
    private ObservationParameterService observationParameterService;
    @Autowired
    private ObservationScopeService observationScopeService;
    @Autowired
    private ObservationTerritoryService observationTerritoryService;
    @Autowired
    private OrganizationService organizationService;

    //LANGUAGE
    @GetMapping("languages")
    public String viewAllLanguages(Model model) {
        List<Language> languages = languageService.listAll();
        model.addAttribute("languages", languages);
        return "view_languages";
    }
    @GetMapping("languages/add")
    public String showNewLanguagePage() {
        return "add_language";
    }

    //RELATED PROJECT
    @GetMapping("related_projects")
    public String viewAllProjectTypes(Model model) {
        List<RelatedProject> relatedProjects = relatedProjectService.listAll();
        model.addAttribute("relatedProjects", relatedProjects);
        return "view_related_projects";
    }
    @GetMapping("related_projects/add")
    public String showNewProjectTypePage() {
        return "add_related_project";
    }

    //COUNTRY
    @GetMapping("countries")
    public String viewAllCountries(Model model) {
        List<Country> countries = countryService.listAll();
        model.addAttribute("countries", countries);
        return "view_countries";
    }
    @GetMapping("countries/add")
    public String showNewCountryPage() {
        return "add_country";
    }

    //ORGANIZATION
    @GetMapping("organizations")
    public String viewAllOrganization(Model model) {
        List<Organization> organizations = organizationService.listAll();
        model.addAttribute("organizations", organizations);
        return "view_organizations";
    }
    @GetMapping("organizations/add")
    public String showNewOrganizationPage(Model model) {
        List<Country> countries = countryService.listAll();
        model.addAttribute("countries", countries);
        return "add_organization";
    }

    //OBSERVATION METHOD
    @GetMapping("observation_methods")
    public String viewAllObservationMethods(Model model) {
        List<ObservationMethod> observationMethods = observationMethodService.listAll();
        model.addAttribute("observationMethods", observationMethods);
        return "view_observation_methods";
    }
    @GetMapping("observation_methods/add")
    public String showNewObservationMethodPage() {
        return "add_observation_method";
    }

    //OBSERVATION DISCIPLINE
    @GetMapping("observation_disciplines")
    public String viewAllObservationDisciplines(Model model) {
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        model.addAttribute("observationDisciplines", observationDisciplines);
        return "view_observation_disciplines";
    }
    @GetMapping("observation_disciplines/add")
    public String showNewObservationDisciplinePage() {
        return "add_observation_discipline";
    }

    //OBSERVATION TYPE
    @GetMapping("observation_types")
    public String viewAllObservationTypes(Model model) {
        List<ObservationType> observationTypes = observationTypeService.listAll();
        model.addAttribute("observationTypes", observationTypes);
        return "view_observation_types";
    }
    @GetMapping("observation_types/add")
    public String showNewObservationTypePage(Model model) {
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        model.addAttribute("observationDisciplines", observationDisciplines);
        return "add_observation_type";
    }

    // OBSERVATION PARAMETER
    @GetMapping("observation_parameters")
    public String viewAllObservationParameters(Model model) {
        List<ObservationParameter> observationParameters = observationParameterService.listAll();
        model.addAttribute("observationParameters", observationParameters);
        return "view_observation_parameters";
    }
    @GetMapping("observation_parameters/add")
    public String showNewObservationParameterPage(Model model) {
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        model.addAttribute("observationDisciplines", observationDisciplines);
        return "add_observation_parameter";
    }

    //OBSERVATION SCOPE
    @GetMapping("observation_scopes")
    public String viewAllObservationScope(Model model) {
        List<ObservationScope> observationScopes = observationScopeService.listAll();
        model.addAttribute("observationScopes", observationScopes);
        return "view_observation_scopes";
    }
    @GetMapping("observation_scopes/add")
    public String showNewObservationScopePage() {
        return "add_observation_scope";
    }

    //OBSERVATION TERRITORY
    @GetMapping("observation_territories")
    public String viewAllObservationTerritories(Model model) {
        List<ObservationTerritory> observationTerritories = observationTerritoryService.listAll();
        model.addAttribute("observationTerritories", observationTerritories);
        return "view_observation_territories";
    }
    @GetMapping("observation_territories/add")
    public String showNewObservationTerritoryPage() {
        return "add_observation_territory";
    }
}