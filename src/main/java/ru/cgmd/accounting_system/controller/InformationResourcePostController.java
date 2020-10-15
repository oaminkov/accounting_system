package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repos.UploadedFileRepository;
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
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    public void setInformationResourceManyToManyFields(
            InformationResource informationResource,
            ObservationParameter[] observationParameters,
            ObservationScope[] observationScopes,
            ObservationTerritory[] observationTerritories,
            Organization[] organizations
    ) {
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
            Set<Organization> organizationSet = new HashSet<>();
            for (Organization organization : organizations) {
                if (organization != null) {
                    organizationSet.add(organization);
                }
            }
            informationResource.setOrganizations(organizationSet);
        }
    }

    public List<UploadedFile> saveFiles(InformationResource informationResource, MultipartFile[] files) throws IOException {
        if(files[0].getSize() != 0) {
            List<UploadedFile> uploadedFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null) {
                    String uploadDirPath =  uploadPath + "/" +
                                            informationResource.getCountry().getId() + "/" +
                                            informationResource.getMainOrganization().getId() + "/" +
                                            informationResource.getInventoryNumber();
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
            return uploadedFiles;
        }
        else return null;
    }

    @Transactional
    public void deleteAllAttachedFiles(InformationResource informationResource) {
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationResource(informationResource);
        for (UploadedFile uploadedFile : uploadedFiles) {
            File file = new File(uploadedFile.getPath());

            if (file.exists()) {
                file.delete();
            }
        }
        uploadedFileRepository.deleteByInformationResource(informationResource);
    }

    @PostMapping("information_resources/add")
    public String saveInformationResource(
            @AuthenticationPrincipal User operator,
            @RequestParam String inventoryNumber,
            @RequestParam String fullnameCdrom,
            @RequestParam String abbreviationCdrom,
            @RequestParam String dateObservationStart,
            @RequestParam String dateObservationEnd,
            @RequestParam String briefContent,
            @RequestParam String volume,
            @RequestParam String receivedDate,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam Country country,
            @RequestParam Organization mainOrganization,
            @RequestParam ObservationMethod observationMethod,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(name = "observationParameter") ObservationParameter[] observationParameters,
            @RequestParam(name = "observationScope") ObservationScope[] observationScopes,
            @RequestParam(name = "observationTerritory") ObservationTerritory[] observationTerritories,
            @RequestParam(name = "organization", required = false) Organization[] organizations,
            @RequestParam(name = "uploadFiles") MultipartFile[] files
    ) throws IOException {
        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEntering = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        InformationResource informationResource = new InformationResource(
                inventoryNumber, fullnameCdrom, abbreviationCdrom,
                dateObservationStart, dateObservationEnd,
                briefContent, volume, receivedDate,
                language, relatedProject, country, mainOrganization, observationMethod,
                duplicate, operator, dateOfEntering);

        setInformationResourceManyToManyFields(informationResource, observationParameters, observationScopes, observationTerritories, organizations);

        List<UploadedFile> uploadedFiles = saveFiles(informationResource, files);
        informationResource.setUploadedFiles(uploadedFiles);

        informationResourceService.save(informationResource);
        return "redirect:/information_resources";
    }

    @Transactional
    @PostMapping("/information_resources/edit/{id}")
    public String editInformationResource(
            @PathVariable("id") InformationResource informationResource,
            @AuthenticationPrincipal User editor,
            @RequestParam String inventoryNumber,
            @RequestParam String fullnameCdrom,
            @RequestParam String abbreviationCdrom,
            @RequestParam String dateObservationStart,
            @RequestParam String dateObservationEnd,
            @RequestParam String briefContent,
            @RequestParam String volume,
            @RequestParam String receivedDate,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam Country country,
            @RequestParam Organization mainOrganization,
            @RequestParam ObservationMethod observationMethod,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(name = "observationParameter") ObservationParameter[] observationParameters,
            @RequestParam(name = "observationScope") ObservationScope[] observationScopes,
            @RequestParam(name = "observationTerritory") ObservationTerritory[] observationTerritories,
            @RequestParam(name = "organization", required = false) Organization[] organizations,
            @RequestParam(name = "uploadFiles") MultipartFile[] files,
            @RequestParam(required = false) boolean delAttachedFiles
    ) throws IOException {

        if (delAttachedFiles) {
            deleteAllAttachedFiles(informationResource);
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEdit = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        if (informationResource.getUploadedFiles() != null) {
            Country countryTemp = informationResource.getCountry();
            Organization mainOrganizationTemp = informationResource.getMainOrganization();
            String inventoryNumberTemp = informationResource.getInventoryNumber();

            if (!countryTemp.equals(country) || !mainOrganizationTemp.equals(mainOrganization) || !inventoryNumberTemp.equals(inventoryNumber)) {
                String uploadDirPath =  uploadPath + "/" +
                                        country.getId() + "/" +
                                        mainOrganization.getId() + "/" +
                                        inventoryNumber;
                File uploadDir = new File(uploadDirPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                Set<UploadedFile> uploadedFilesTemp = uploadedFileRepository.findByInformationResource(informationResource);

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
                String oldUploadDirPath =   uploadPath + "/" +
                                            countryTemp.getId() + "/" +
                                            mainOrganizationTemp.getId() + "/" +
                                            inventoryNumberTemp;
                File oldUploadDir = new File(oldUploadDirPath);

                if (oldUploadDir.exists()) {
                    oldUploadDir.delete();
                }
            }
        }

        setInformationResourceManyToManyFields(informationResource, observationParameters, observationScopes, observationTerritories, organizations);

        informationResource.setEditedFields(
                inventoryNumber, fullnameCdrom, abbreviationCdrom,
                dateObservationStart, dateObservationEnd,
                briefContent, volume, receivedDate,
                language, relatedProject, country, mainOrganization, observationMethod,
                duplicate, editor, dateOfEdit);

        List<UploadedFile> uploadedFiles = saveFiles(informationResource, files);
        if (uploadedFiles != null) {
            informationResource.addUploadedFiles(uploadedFiles);
        }

        informationResourceService.save(informationResource);
        return String.format("redirect:/information_resources/edit/%d", informationResource.getId());
    }
}