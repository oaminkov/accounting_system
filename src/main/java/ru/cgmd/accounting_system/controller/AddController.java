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

    //Делает первую букву слова заглавной, остальные - строчными
    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    //COUNTRY
    @PostMapping("/add_country")
    public String saveCountry(@RequestParam("nameCountry") String nameCountry, Model model) {
        nameCountry = nameCountry.trim();

        if (!nameCountry.isEmpty()) {
            if (!nameCountry.matches("^[A-Za-zА-Яа-я ]+$")) {
                model.addAttribute("messageError", "Название страны может содержать только пробелы, русские и английские буквы");
                return "add_country";
            }

            nameCountry = firstUpperCase(nameCountry);

            if (!countryService.isExists(nameCountry)) {
                Country country = new Country(nameCountry);
                countryService.save(country);
                return "redirect:/view_country";
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
    //GEOGRAPHICAL OBJECT
    @PostMapping("/add_geographicalobject")
    public String saveGeographicalObject(@RequestParam("nameGeographicalObject") String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!geographicalObjectService.isExists(name)) {
                GeographicalObject geographicalObject = new GeographicalObject(name);
                geographicalObjectService.save(geographicalObject);
                return "redirect:/view_geographicalobject";
            }
            else {
                model.addAttribute("messageError", "Такой географический объект уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_geographicalobject";
    }
    //LANGUAGE
    @PostMapping("/add_language")
    public String saveLanguage(@RequestParam("nameLanguage") String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!languageService.isExists(name)) {
                Language language = new Language(name);
                languageService.save(language);
                return "redirect:/view_language";
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
    //OBSERVATION DISCIPLINE
    @PostMapping(value = "add_observationdiscipline")
    public String saveObservationDiscipline(@RequestParam("nameObservationDiscipline") String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationDisciplineService.isExists(name)) {
                ObservationDiscipline observationDiscipline = new ObservationDiscipline(name);
                observationDisciplineService.save(observationDiscipline);
                return "redirect:/view_observationdiscipline";
            }
            else {
                model.addAttribute("messageError", "Такая дисциплина наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observationdiscipline";
    }
    //OBSERVATION TYPE
    @PostMapping(value = "add_observationtype")
    public String saveObservationType(@RequestParam("nameObservationType") String nameObservationType,
                                      @RequestParam("observationDiscipline") ObservationDiscipline observationDiscipline) {
        ObservationType observationType = new ObservationType(nameObservationType, observationDiscipline);
        observationTypeService.save(observationType);
        return "redirect:/view_observationtype";
    }
    //OBSERVATION SCOPE
    @PostMapping(value = "add_observationscope")
    public String saveObservationScope(@RequestParam("nameObservationScope") String name, Model model) {
        name = name.trim();

        if(!name.isEmpty()) {
            name = firstUpperCase(name);

            if(!observationScopeService.isExists(name)) {
                ObservationScope observationScope = new ObservationScope(name);
                observationScopeService.save(observationScope);
                return "redirect:/view_observationscope";
            }
            else {
                model.addAttribute("messageError", "Такая сфера наблюдений уже есть в базе");
            }
        }
        else {
            model.addAttribute("messageError", "Вы ввели пустую строку");
        }
        return "add_observationscope";
    }
    //ORGANIZATION
    @PostMapping("/add_organization")
    public String saveOrganization(@ModelAttribute("organization") Organization organization) {
        organizationService.save(organization);
        return "redirect:/view_organization";
    }
    //PROJECT OR PROGRAM
    @PostMapping(value = "/add_projectorprogram")
    public String saveProjectOrProgram(
            @RequestParam("choiceProjectOrProgram") String choiceProjectOrProgram,
            @RequestParam("fullnameProjectOrProgram") String fullnameProjectOrProgram,
            @RequestParam("abbreviationProjectOrProgram") String abbreviationProjectOrProgram) {
        ProjectOrProgram projectOrProgram = new ProjectOrProgram(choiceProjectOrProgram, fullnameProjectOrProgram, abbreviationProjectOrProgram);
        projectOrProgramService.save(projectOrProgram);
        return "redirect:/view_projectorprogram";
    }
}