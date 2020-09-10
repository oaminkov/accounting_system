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
    private CountryService countryService;
    @Autowired
    private GeographicalObjectService geographicalObjectService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ProjectOrProgramService projectOrProgramService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationScopeService observationScopeService;
    @Autowired
    private ObservationTypeService observationTypeService;
    @Autowired
    private OrganizationService organizationService;

    //COUNTRY
    @GetMapping("/view_country")
    public String viewAllCountry(@AuthenticationPrincipal User user, Model model) {
        List<Country> listCountry = countryService.listAll();
        model.addAttribute("listCountry", listCountry);
        return "view_country";
    }
    @GetMapping("/add_country")
    public String showNewCountryPage(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "add_country";
    }

    //GEOGRAPHICAL OBJECT
    @GetMapping("/view_geographicalobject")
    public String viewAllGeographicalObject(Model model) {
        List<GeographicalObject> listGeographicalObject = geographicalObjectService.listAll();
        model.addAttribute("listGeographicalObject", listGeographicalObject);
        return "view_geographicalobject";
    }
    @GetMapping("/add_geographicalobject")
    public String showNewGeographicalObjectPage(Model model) {
        GeographicalObject geographicalObject = new GeographicalObject();
        model.addAttribute("geographicalObject", geographicalObject);
        return "add_geographicalobject";
    }

    //LANGUAGE
    @GetMapping("/view_language")
    public String viewAllLanguage(Model model) {
        List<Language> listLanguage = languageService.listAll();
        model.addAttribute("listLanguage", listLanguage);
        return "view_language";
    }
    @GetMapping("/add_language")
    public String showNewLanguagePage(Model model) {
        Language language = new Language();
        model.addAttribute("language", language);
        return "add_language";
    }

    //OBSERVATION DISCIPLINE
    @GetMapping("/view_observationdiscipline")
    public String viewAllObservationDiscipline(Model model) {
        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);
        return "view_observationdiscipline";
    }
    @GetMapping("/add_observationdiscipline")
    public String showNewObservationDisciplinePage(Model model) {
        ObservationDiscipline observationDiscipline = new ObservationDiscipline();
        model.addAttribute("observationDiscipline", observationDiscipline);
        return "add_observationdiscipline";
    }

    //OBSERVATION TYPE
    @GetMapping("/view_observationtype")
    public String viewAllObservationType(Model model) {
        List<ObservationType> listObservationType = observationTypeService.listAll();
        model.addAttribute("listObservationType", listObservationType);
        return "view_observationtype";
    }
    @GetMapping("/add_observationtype")
    public String showNewObservationTypePage(Model model) {
        ObservationType observationType = new ObservationType();
        model.addAttribute("observationType", observationType);

        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);
        return "add_observationtype";
    }

    //OBSERVATION SCOPE
    @GetMapping("/view_observationscope")
    public String viewAllObservationScope(Model model) {
        List<ObservationScope> listObservationScope = observationScopeService.listAll();
        model.addAttribute("listObservationScope", listObservationScope);
        return "view_observationscope";
    }
    @GetMapping("/add_observationscope")
    public String showNewObservationScopePage(Model model) {
        ObservationScope observationScope = new ObservationScope();
        model.addAttribute("observationScope", observationScope);
        return "add_observationscope";
    }

    //ORGANIZATION
    @GetMapping("/view_organization")
    public String viewAllOrganization(Model model) {
        List<Organization> listOrganization = organizationService.listAll();
        model.addAttribute("listOrganization", listOrganization);
        return "view_organization";
    }
    @GetMapping("/add_organization")
    public String showNewOrganizationPage(Model model) {
        List<Country> listCountry = countryService.listAll(); //select стран
        model.addAttribute("listCountry", listCountry);

        Organization organization = new Organization();
        model.addAttribute("organization", organization);
        return "add_organization";
    }

    //PROJECT OR PROGRAM
    @GetMapping("/view_projectorprogram")
    public String viewAllProjectOrProgram(Model model) {
        List<ProjectOrProgram> listProjectOrProgram = projectOrProgramService.listAll();
        model.addAttribute("listProjectOrProgram", listProjectOrProgram);
        return "view_projectorprogram";
    }
    @GetMapping("/add_projectorprogram")
    public String showNewProjectOrProgramPage(Model model) {
        ProjectOrProgram projectOrProgram = new ProjectOrProgram();
        model.addAttribute("projectOrProgram", projectOrProgram);
        return "add_projectorprogram";
    }
}