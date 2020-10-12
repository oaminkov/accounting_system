package ru.cgmd.accounting_system.controller;

import org.springframework.stereotype.Controller;

@Controller
public class SearchController {
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
