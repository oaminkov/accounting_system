package ru.cgmd.accounting_system.controller;

import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ProjectTypeService projectTypeService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ObservationMethodService observationMethodService;
    @Autowired
    private GeographicalObjectService geographicalObjectService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationScopeService observationScopeService;
    @Autowired
    private ObservationTypeService observationTypeService;
    @Autowired
    private OrganizationService organizationService;

    //LANGUAGE
    @GetMapping("languages")
    public String viewAllLanguages(Model model) {
        List<Language> listLanguage = languageService.listAll();
        model.addAttribute("listLanguage", listLanguage);
        return "view_languages";
    }
    @GetMapping("languages/add")
    public String showNewLanguagePage(Model model) {
        Language language = new Language();
        model.addAttribute("language", language);
        return "add_language";
    }

    //PROJECT TYPE
    @GetMapping("project_types")
    public String viewAllProjectTypes(Model model) {
        List<ProjectType> listProjectType = projectTypeService.listAll();
        model.addAttribute("listProjectType", listProjectType);
        return "view_project_types";
    }
    @GetMapping("project_types/add")
    public String showNewProjectTypePage(Model model) {
        ProjectType projectType = new ProjectType();
        model.addAttribute("projectOrProgram", projectType);
        return "add_project_type";
    }

    //COUNTRY
    @GetMapping("countries")
    public String viewAllCountries(@AuthenticationPrincipal User user, Model model) {
        List<Country> listCountry = countryService.listAll();
        model.addAttribute("listCountry", listCountry);
        return "view_countries";
    }
    @GetMapping("countries/add")
    public String showNewCountryPage(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "add_country";
    }

    //OBSERVATION METHOD
    @GetMapping("observation_methods")
    public String viewAllObservationMethods(@AuthenticationPrincipal User user, Model model) {
        List<ObservationMethod> observationMethods = observationMethodService.listAll();
        model.addAttribute("listObservationMethods", observationMethods);
        return "view_observation_methods";
    }
    @GetMapping("observation_methods/add")
    public String showNewObservationMethodPage(Model model) {
        ObservationMethod observationMethod = new ObservationMethod();
        model.addAttribute("observationMethod", observationMethod);
        return "add_observation_method";
    }

    //GEOGRAPHICAL OBJECT
    @GetMapping("geographical_objects")
    public String viewAllGeographicalObject(Model model) {
        List<GeographicalObject> listGeographicalObject = geographicalObjectService.listAll();
        model.addAttribute("listGeographicalObject", listGeographicalObject);
        return "view_geographical_objects";
    }
    @GetMapping("geographical_objects/add")
    public String showNewGeographicalObjectPage(Model model) {
        GeographicalObject geographicalObject = new GeographicalObject();
        model.addAttribute("geographicalObject", geographicalObject);
        return "add_geographical_object";
    }

    //OBSERVATION DISCIPLINE
    @GetMapping("observation_disciplines")
    public String viewAllObservationDiscipline(Model model) {
        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);
        return "view_observation_disciplines";
    }
    @GetMapping("observation_disciplines/add")
    public String showNewObservationDisciplinePage(Model model) {
        ObservationDiscipline observationDiscipline = new ObservationDiscipline();
        model.addAttribute("observationDiscipline", observationDiscipline);
        return "add_observation_discipline";
    }

    //OBSERVATION TYPE
    @GetMapping("observation_types")
    public String viewAllObservationType(Model model) {
        List<ObservationType> listObservationType = observationTypeService.listAll();
        model.addAttribute("listObservationType", listObservationType);
        return "view_observation_types";
    }
    @GetMapping("observation_types/add")
    public String showNewObservationTypePage(Model model) {
        ObservationType observationType = new ObservationType();
        model.addAttribute("observationType", observationType);

        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);
        return "add_observation_type";
    }

    //OBSERVATION SCOPE
    @GetMapping("observation_scopes")
    public String viewAllObservationScope(Model model) {
        List<ObservationScope> listObservationScope = observationScopeService.listAll();
        model.addAttribute("listObservationScope", listObservationScope);
        return "view_observation_scopes";
    }
    @GetMapping("observation_scopes/add")
    public String showNewObservationScopePage(Model model) {
        ObservationScope observationScope = new ObservationScope();
        model.addAttribute("observationScope", observationScope);
        return "add_observation_scope";
    }

    //ORGANIZATION
    @GetMapping("organizations")
    public String viewAllOrganization(Model model) {
        List<Organization> listOrganization = organizationService.listAll();
        model.addAttribute("listOrganization", listOrganization);
        return "view_organizations";
    }
    @GetMapping("organizations/add")
    public String showNewOrganizationPage(Model model) {
        List<Country> listCountry = countryService.listAll(); //select стран
        model.addAttribute("listCountry", listCountry);

        Organization organization = new Organization();
        model.addAttribute("organization", organization);
        return "add_organization";
    }
}