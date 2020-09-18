package ru.cgmd.accounting_system.controller;

import ru.cgmd.accounting_system.classes.Container;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.*;
import ru.cgmd.accounting_system.repos.InformationProductRepository;
import ru.cgmd.accounting_system.repos.ObservationTypeRepository;
import ru.cgmd.accounting_system.repos.UploadedFileRepository;
import ru.cgmd.accounting_system.repos.UserRepository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObservationTypeRepository observationTypeRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationTypeService observationTypeService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private InformationProductRepository informationProductRepository;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Autowired
    private UserService userService;

    public void isUserAuthorized(@AuthenticationPrincipal User user, Model model)
    {
        if (user != null) {
            model.addAttribute("loggedUser", user);
        }
        else{
            model.addAttribute("message", "Вы не авторизованы!");
        }
    }

    public void isAdminExists() {
        User userFromDb = userRepository.findByUsername("admin");

        if (userFromDb == null){
            User user = new User();

            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);

            user.setUsername("admin");
            user.setPassword("qwe");
            user.setActive(true);
            user.setRoles(roles);
            userService.addUser(user);
        }
    }

    @GetMapping("/")
    public String redirMainPage(@AuthenticationPrincipal User user, Model model){
        isUserAuthorized(user, model);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String viewMainPage(@AuthenticationPrincipal User user, Model model){
        isAdminExists(); //если админа не существует, добавляем
        isUserAuthorized(user, model);
        return "main";
    }

    //SEARCH START
    @GetMapping("/search")
    public String viewSearchPage(@AuthenticationPrincipal User user, Model model){
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.findByInformationProductsExists();
        List<ObservationType> observationTypes = observationTypeService.findByInformationProductsExists();
        List<Country> countries = countryService.findByInformationProductsExists();
        List<Organization> organizations = organizationService.findByInformationProductsExists();

        model.addAttribute("observationDisciplines", observationDisciplines);

        return "search";
    }

    @PostMapping("/search")
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

        return "view_informationproduct";
    }
    //SEARCH END

    @GetMapping("/login")
    public String viewLoginPage(@AuthenticationPrincipal User loggedUser, Model model){
        if (loggedUser != null) {
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("message", "Вы уже авторизованы *o*");
        }
        else{
            model.addAttribute("message", "Авторизуйтесь ^_^");
        }
        return("login");
    }

    @GetMapping("/getObservationTypeList")
    public @ResponseBody
    String ajaxResp(@RequestParam("idObservationDiscipline") ObservationDiscipline observationDiscipline) {
        List<Container> containers = new ArrayList<Container>();

        List<ObservationType> observationTypes = observationTypeRepository.findByObservationDiscipline(observationDiscipline);

        for (ObservationType observationType : observationTypes) {
            Container container = new Container();
            container.setId(observationType.getId());
            container.setName(observationType.getName());
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

        List<ObservationType> observationTypes = observationTypeRepository.findByObservationDiscipline(observationDiscipline);

        for (ObservationType observationType : observationTypes) {
            if (!observationType.getInformationProducts().isEmpty()) {
                Container container = new Container();
                container.setId(observationType.getId());
                container.setName(observationType.getName());
                containers.add(container);
            }
        }

        String json = new Gson().toJson(containers);
        return json;
    }

    @GetMapping("/getCountryList1")
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
    }

    @GetMapping("/getOrganizationList1")
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
    }
}
