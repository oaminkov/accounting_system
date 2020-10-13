package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.service.InformationResourceService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class InformationResourcePostController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private InformationResourceService informationResourceService;

    @PostMapping("information_resources/add")
    public String saveInformationResource(
            @AuthenticationPrincipal User operator,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam Country country,
            @RequestParam ObservationMethod observationMethod,
            @RequestParam String inventoryNumber,
            @RequestParam String fullnameCdrom,
            @RequestParam String abbreviationCdrom,
            @RequestParam String dateObservationStart,
            @RequestParam String dateObservationEnd,
            @RequestParam String briefContent,
            @RequestParam String volume,
            @RequestParam String receivedDate,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(value = "uploadFiles") MultipartFile[] files,
            @RequestParam(value = "observationParameter") ObservationParameter[] observationParameters,
            @RequestParam(value = "observationScope") ObservationScope[] observationScopes,
            @RequestParam(value = "observationTerritory") ObservationTerritory[] observationTerritories,
            @RequestParam(value = "organization") Organization[] organizations
    ) throws IOException {

        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEntering = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        InformationResource informationResource = new InformationResource(
                        operator, language, relatedProject, country, observationMethod,
                        inventoryNumber, fullnameCdrom, abbreviationCdrom, dateObservationStart, dateObservationEnd,
                        briefContent, volume, receivedDate, duplicate, dateOfEntering);

        if (observationParameters != null) {
            Set<ObservationDiscipline> observationDisciplineSet = new HashSet<>();
            Set<ObservationType> observationTypeSet = new HashSet<>();
            Set<ObservationParameter> observationParameterSet = new HashSet<>();

            for (ObservationParameter observationParameter : observationParameters) {
                if (observationParameter != null) {
                    observationDisciplineSet.add(observationParameter.getObservationType().getObservationDiscipline());
                    observationTypeSet.add(observationParameter.getObservationType());
                    observationParameterSet.add(observationParameter);
                }
            }

            informationResource.setObservationDisciplines(observationDisciplineSet);
            informationResource.setObservationTypes(observationTypeSet);
            informationResource.setObservationParameters(observationParameterSet);
        }

        if (observationScopes != null) {
            Set<ObservationScope> observationScopeSet = new HashSet<>();

            for (ObservationScope observationScope : observationScopes) {
                if (observationScope != null) {
                    observationScopeSet.add(observationScope);
                }
            }

            informationResource.setObservationScopes(observationScopeSet);
        }

        if (observationTerritories != null) {
            Set<ObservationTerritory> observationTerritorySet = new HashSet<>();

            for (ObservationTerritory observationTerritory : observationTerritories) {
                if (observationTerritory != null) {
                    observationTerritorySet.add(observationTerritory);
                }
            }

            informationResource.setObservationTerritories(observationTerritorySet);
        }

        if (organizations != null) {
            List<InfresOrganization> infresOrganizationList = new ArrayList<>();
            boolean main = true;

            for (Organization organization : organizations) {
                if (organization != null) {
                    infresOrganizationList.add(new InfresOrganization(main, informationResource, organization));

                    if (main) {
                        main = false;
                    }
                }
            }

            informationResource.setInfresOrganizations(infresOrganizationList);
        }

        List<UploadedFile> uploadedFiles = new ArrayList<>();
        if(files[0].getSize() != 0) {
            for (MultipartFile file : files) {
                if (file != null) {
                    String uploadDirPath = uploadPath + "/" + country.getId() + "/" + inventoryNumber; //нужно редачить
                    File uploadDir = new File(uploadDirPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();
                    String resultFilepath = uploadDirPath + "/" + resultFilename;

                    file.transferTo(new File(resultFilepath));

                    uploadedFiles.add(new UploadedFile(resultFilename, resultFilepath, informationResource));
                }
            }
            informationResource.setUploadedFiles(uploadedFiles);
        }

        informationResourceService.save(informationResource);

        return "redirect:/information_resources";
    }

    /*@PostMapping("/information_resources/edit/{id}")
    public String editInformationResource(
            @PathVariable("id") InformationProduct informationProduct,
            @AuthenticationPrincipal User editor,
            @RequestParam ProjectOrProgram projectOrProgram,
            @RequestParam Country country,
            @RequestParam Language language,
            @RequestParam ObservationDiscipline observationDiscipline,
            @RequestParam ObservationType observationType,
            @RequestParam ObservationScope observationScope,
            @RequestParam ObservationTerritory geographicalObject,
            @RequestParam Organization organization,
            @RequestParam String inventoryNumber,
            @RequestParam String fullnameCdrom,
            @RequestParam String abbreviationCdrom,
            @RequestParam String dateObservationStart,
            @RequestParam String dateObservationEnd,
            @RequestParam String volume,
            @RequestParam String receivedDate,
            @RequestParam String briefContent,
            @RequestParam(value = "duplicate", required = false, defaultValue = "Нет") String duplicate,
            @RequestParam(value = "uploadFiles") MultipartFile[] files
    ) throws IOException {
        boolean dup;
        if (duplicate.equals("Да")) {
            dup = true;
        }
        else {
            dup = false;
        }

        Organization organizationTemp = informationProduct.getOrganization();
        String inventoryNumberTemp = informationProduct.getInventoryNumber();

        if (!organizationTemp.equals(organization) || !inventoryNumberTemp.equals(inventoryNumber))
        {
            String uploadDirPath = uploadPath + "/" + country.getId() + "/" + inventoryNumber;
            File uploadDir = new File(uploadDirPath);


            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            Set<UploadedFile> uploadedFilesTemp = uploadedFileRepository.findByInformationProduct(informationProduct);

            for (UploadedFile uploadedFile : uploadedFilesTemp) {
                File file = new File(uploadedFile.getPath());
                String filename = file.getName();
                String filepath = uploadDirPath + "/" + filename;

                if (file.exists()) {
                    file.renameTo(new File(filepath));
                }
                uploadedFile.setPath(filepath);
                uploadedFileRepository.save(uploadedFile);
            }
            String oldUploadDirPath = uploadPath + "/" + organizationTemp.getId() + "/" + inventoryNumberTemp;
            File oldUploadDir = new File(oldUploadDirPath);

            if (oldUploadDir.exists()) {
                oldUploadDir.delete();
            }
        }

        informationProduct.setProjectOrProgram(projectOrProgram);
        informationProduct.setCountry(country);
        informationProduct.setLanguage(language);
        informationProduct.setObservationDiscipline(observationDiscipline);
        informationProduct.setObservationType(observationType);
        informationProduct.setObservationScope(observationScope);
        informationProduct.setObservationTerritory(geographicalObject);
        informationProduct.setOrganization(organization);
        informationProduct.setInventoryNumber(inventoryNumber);
        informationProduct.setFullnameCdrom(fullnameCdrom);
        informationProduct.setAbbreviationCdrom(abbreviationCdrom);
        informationProduct.setDateObservationStart(dateObservationStart);
        informationProduct.setDateObservationEnd(dateObservationEnd);
        informationProduct.setVolume(volume);
        informationProduct.setReceivedDate(receivedDate);
        informationProduct.setBriefContent(briefContent);
        informationProduct.setDuplicate(dup);

        List<UploadedFile> uploadedFiles = new ArrayList<>();

        if(files[0].getSize() != 0) {
            for (MultipartFile file : files) {
                if (file != null) {
                    String uploadDirPath = uploadPath + "/" + organization.getId() + "/" + inventoryNumber;
                    File uploadDir = new File(uploadDirPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();
                    String resultFilepath = uploadDirPath + "/" + resultFilename;

                    file.transferTo(new File(resultFilepath));

                    uploadedFiles.add(new UploadedFile(resultFilename, resultFilepath, informationProduct));
                }
            }
            informationProduct.addUploadedFiles(uploadedFiles);
        }

        informationProduct.setEditor(editor);

        LocalDateTime myDateObj = LocalDateTime.now();
        String formattedDate = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        informationProduct.setDateOfEdit(formattedDate);
        informationProductService.save(informationProduct);

        return "redirect:/information_product/edit/" + informationProduct.getId();
    }*/

    /*@Transactional
    @PostMapping("information_product/delete_files/{id}")
    public String deleteFiles(@PathVariable("id") InformationProduct informationProduct, Model model) {
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        for (UploadedFile uploadedFile : uploadedFiles) {
            File file = new File(uploadedFile.getPath());

            if (file.exists()) {
                file.delete();
            }
        }

        uploadedFileRepository.deleteByInformationProduct(informationProduct);
        uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        List<Country> listCountry = countryService.listAll(); //select стран
        model.addAttribute("listCountry", listCountry);

        List<ObservationTerritory> listObservationTerritory = geographicalObjectService.listAll(); //select географического объекта
        model.addAttribute("listObservationTerritory", listObservationTerritory);

        List<Language> listLanguage = languageService.listAll(); //select языка
        model.addAttribute("listLanguage", listLanguage);

        List<ProjectOrProgram> listProjectOrProgram = projectOrProgramService.listAll(); //проекты/программы
        model.addAttribute("listProjectOrProgram", listProjectOrProgram);

        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);

        List<ObservationType> listObservationType = observationTypeRepository.findByObservationDiscipline(informationProduct.getObservationDiscipline());
        model.addAttribute("listObservationType", listObservationType);

        List<ObservationScope> listObservationScope = observationScopeService.listAll();
        model.addAttribute("listObservationScope", listObservationScope);

        List<Organization> listOrganization = organizationService.listAll();
        model.addAttribute("listOrganization", listOrganization);

        model.addAttribute("informationProduct",informationProduct);
        model.addAttribute("uploadedFiles", uploadedFiles);

        return "/update_informationproduct";
    }*/
}