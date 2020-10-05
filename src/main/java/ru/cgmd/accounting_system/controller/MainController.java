package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.cgmd.accounting_system.domain.Role;
import ru.cgmd.accounting_system.domain.User;
import ru.cgmd.accounting_system.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    public void isUserAuthorized(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("loggedUser", user);
        }
        else {
            model.addAttribute("message", "Вы не авторизованы!");
        }
    }

    public void isAdminExists() {
        UserDetails userFromDb = userService.loadUserByUsername("admin");

        if (userFromDb == null) {
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
    public String redirectMainPage(@AuthenticationPrincipal User user, Model model){
        isUserAuthorized(user, model);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String viewMainPage(@AuthenticationPrincipal User user, Model model){
        isAdminExists(); //если админа не существует, добавляем
        isUserAuthorized(user, model);
        return "main";
    }

    @GetMapping("/login")
    public String viewLoginPage(@AuthenticationPrincipal User loggedUser, Model model){
        if (loggedUser != null) {
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("message", "Вы уже авторизованы *o*");
        }
        else {
            model.addAttribute("message", "Авторизуйтесь ^_^");
        }
        return("login");
    }

    //SEARCH START
    /*@GetMapping("/search")
    public String viewSearchPage(@AuthenticationPrincipal User user, Model model){
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.findByInformationProductsExists();
        List<ObservationType> observationTypes = observationTypeService.findByInformationProductsExists();
        List<Country> countries = countryService.findByInformationProductsExists();
        List<Organization> organizations = organizationService.findByInformationProductsExists();

        model.addAttribute("observationDisciplines", observationDisciplines);

        return "search";
    }*/

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

        return "view_information_product";
    }*/
    //SEARCH END
}
