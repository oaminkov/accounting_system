package ru.cgmd.accounting_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.*;

@Controller
public class AddController {
    private final LanguageService languageService;
    private final RelatedProjectService relatedProjectService;
    private final CountryService countryService;
    private final ObservationMethodService observationMethodService;
    private final ObservationDisciplineService observationDisciplineService;
    private final ObservationTypeService observationTypeService;
    private final ObservationParameterService observationParameterService;
    private final ObservationScopeService observationScopeService;
    private final ObservationTerritoryService observationTerritoryService;
    private final OrganizationService organizationService;

    public AddController(
            LanguageService languageService,
            RelatedProjectService relatedProjectService,
            CountryService countryService,
            ObservationMethodService observationMethodService,
            ObservationDisciplineService observationDisciplineService,
            ObservationTypeService observationTypeService,
            ObservationParameterService observationParameterService,
            ObservationScopeService observationScopeService,
            ObservationTerritoryService observationTerritoryService,
            OrganizationService organizationService
    ) {
        this.languageService = languageService;
        this.relatedProjectService = relatedProjectService;
        this.countryService = countryService;
        this.observationMethodService = observationMethodService;
        this.observationDisciplineService = observationDisciplineService;
        this.observationTypeService = observationTypeService;
        this.observationParameterService = observationParameterService;
        this.observationScopeService = observationScopeService;
        this.observationTerritoryService = observationTerritoryService;
        this.organizationService = organizationService;
    }

    //Делает первую букву слова заглавной, остальные - строчными
    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    //RELATED PROJECT
    @PostMapping("related_projects/add")
    public String saveRelatedProject(
            @RequestParam String name,
            @RequestParam String abbreviation,
            @RequestParam String type,
            Model model
    ) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!relatedProjectService.isExists(name, type)) {
                RelatedProject relatedProject = new RelatedProject(name, abbreviation, type);
                relatedProjectService.save(relatedProject);
                return "redirect:/related_projects";
            }
            else {
                model.addAttribute("messageError", "Такой связанный проект уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустое название");
        }
        return "add_related_project";
    }
    //LANGUAGE
    @PostMapping("languages/add")
    public String saveLanguage(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!languageService.isExists(name)) {
                Language language = new Language(name);
                languageService.save(language);
                return "redirect:/languages";
            }
            else {
                model.addAttribute("messageError", "Такой язык уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_language";
    }
    //COUNTRY
    @PostMapping("countries/add")
    public String saveCountry(@RequestParam String name, Model model) {
        name = name.trim();

        if (!name.isEmpty()) {
            if (!name.matches("^[A-Za-zА-Яа-я ]+$")) {
                model.addAttribute("messageError", "Название страны может содержать только пробелы, русские и английские буквы");
                return "add_country";
            }

            name = firstUpperCase(name);

            if (!countryService.isExists(name)) {
                Country country = new Country(name);
                countryService.save(country);
                return "redirect:/countries";
            }
            else {
                model.addAttribute("messageError", "Такая страна уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_country";
    }
    //ORGANIZATION
    @PostMapping("organizations/add")
    public String saveOrganization(@ModelAttribute Organization organization) {
        organizationService.save(organization);
        return "redirect:/organizations";
    }
    //OBSERVATION METHOD
    @PostMapping("observation_methods/add")
    public String saveObservationMethod(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationMethodService.isExists(name)) {
                ObservationMethod observationMethod = new ObservationMethod(name);
                observationMethodService.save(observationMethod);
                return "redirect:/observation_methods";
            }
            else {
                model.addAttribute("messageError", "Такой метод наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observation_method";
    }
    //OBSERVATION DISCIPLINE
    @PostMapping("observation_disciplines/add")
    public String saveObservationDiscipline(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationDisciplineService.isExists(name)) {
                ObservationDiscipline observationDiscipline = new ObservationDiscipline(name);
                observationDisciplineService.save(observationDiscipline);
                return "redirect:/observation_disciplines";
            }
            else {
                model.addAttribute("messageError", "Такая дисциплина наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observation_discipline";
    }
    //OBSERVATION TYPE
    @PostMapping("observation_types/add")
    public String saveObservationType(@RequestParam String name,
                                      @RequestParam ObservationDiscipline observationDiscipline) {
        ObservationType observationType = new ObservationType(name, observationDiscipline);
        observationTypeService.save(observationType);
        return "redirect:/observation_types";
    }
    //OBSERVATION PARAMETER
    @PostMapping("observation_parameters/add")
    public String saveObservationParameter(
            @RequestParam String name,
            @RequestParam ObservationType observationType) {
        ObservationParameter observationParameter = new ObservationParameter(name, observationType);
        observationParameterService.save(observationParameter);
        return "redirect:/observation_parameters";
    }

    //OBSERVATION SCOPE
    @PostMapping("observation_scopes/add")
    public String saveObservationScope(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationScopeService.isExists(name)) {
                ObservationScope observationScope = new ObservationScope(name);
                observationScopeService.save(observationScope);
                return "redirect:/observation_scopes";
            }
            else {
                model.addAttribute("messageError", "Такая сфера наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observation_scope";
    }
    //OBSERVATION TERRITORY
    @PostMapping("observation_territories/add")
    public String saveObservationTerritory(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationTerritoryService.isExists(name)) {
                ObservationTerritory observationTerritory = new ObservationTerritory(name);
                observationTerritoryService.save(observationTerritory);
                return "redirect:/observation_territories";
            }
            else {
                model.addAttribute("messageError", "Такая территория наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observation_territory";
    }
}