package ru.cgmd.accounting_system.controller;

import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {
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

    //Делает первую букву слова заглавной, остальные - строчными
    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
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

    //PROJECT TYPE
    @PostMapping("project_types/add")
    public String saveProjectOrProgram(
            @RequestParam("choiceProjectOrProgram") String type,
            @RequestParam("fullnameProjectOrProgram") String fullnameProjectOrProgram,
            @RequestParam("abbreviationProjectOrProgram") String abbreviationProjectOrProgram) {
        ProjectType projectType = new ProjectType(type, fullnameProjectOrProgram, abbreviationProjectOrProgram);
        projectTypeService.save(projectType);
        return "redirect:/project_types";
    }

    //COUNTRY
    @PostMapping("countries/add")
    public String saveCountry(@RequestParam("nameCountry") String name, Model model) {
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

    //GEOGRAPHICAL OBJECT
    @PostMapping("geographical_objects/add")
    public String saveGeographicalObject(@RequestParam String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!geographicalObjectService.isExists(name)) {
                GeographicalObject geographicalObject = new GeographicalObject(name);
                geographicalObjectService.save(geographicalObject);
                return "redirect:/geographical_objects";
            }
            else {
                model.addAttribute("messageError", "Такой географический объект уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_geographical_object";
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
    public String saveObservationType(@RequestParam("nameObservationType") String nameObservationType,
                                      @RequestParam("observationDiscipline") ObservationDiscipline observationDiscipline) {
        ObservationType observationType = new ObservationType(nameObservationType, observationDiscipline);
        observationTypeService.save(observationType);
        return "redirect:/observation_types";
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
    //ORGANIZATION
    @PostMapping("organizations/add")
    public String saveOrganization(@ModelAttribute("organization") Organization organization) {
        organizationService.save(organization);
        return "redirect:/organizations";
    }
}