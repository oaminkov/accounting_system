package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repo.UploadedFileRepository;
import ru.cgmd.accounting_system.service.InformationResourceService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("information_resources")
public class InformationResourcePostController {
    @Value("${upload.path}")
    private String uploadPath;

    private final InformationResourceService informationResourceService;
    private final UploadedFileRepository uploadedFileRepository;

    public InformationResourcePostController(InformationResourceService informationResourceService, UploadedFileRepository uploadedFileRepository) {
        this.informationResourceService = informationResourceService;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @PostMapping("add")
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
            @RequestParam ResourceType resourceType,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam ObservationMethod observationMethod,
            @RequestParam Country country,
            @RequestParam Organization mainOrganization,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(name = "observationDiscipline", required = false) ObservationDiscipline[] observationDisciplines,
            @RequestParam(name = "observationType", required = false) ObservationType[] observationTypes,
            @RequestParam(name = "observationParameter", required = false) ObservationParameter[] observationParameters,
            @RequestParam(name = "organization", required = false) Organization[] organizations,
            @RequestParam(name = "observationScope", required = false) ObservationScope[] observationScopes,
            @RequestParam(name = "observationTerritory", required = false) ObservationTerritory[] observationTerritories,
            @RequestParam(name = "uploadFiles", required = false) MultipartFile[] files
    ) throws IOException {
        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEntering = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        InformationResource informationResource = new InformationResource(
                inventoryNumber, fullnameCdrom, abbreviationCdrom,
                dateObservationStart, dateObservationEnd,
                briefContent, volume, receivedDate,
                resourceType, language, relatedProject,
                observationMethod, country, mainOrganization,
                duplicate, dateOfEntering, operator
        );

        setInformationResourceManyToManyFields(
                informationResource,
                observationDisciplines, observationTypes, observationParameters,
                organizations, observationScopes, observationTerritories
        );

        List<UploadedFile> uploadedFiles = saveFiles(informationResource, files);
        informationResource.setUploadedFiles(uploadedFiles);

        informationResourceService.save(informationResource);
        return "redirect:/information_resources";
    }

    @Transactional
    @PostMapping("edit/{id}")
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
            @RequestParam ResourceType resourceType,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam ObservationMethod observationMethod,
            @RequestParam Country country,
            @RequestParam Organization mainOrganization,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(name = "observationDiscipline", required = false) ObservationDiscipline[] observationDisciplines,
            @RequestParam(name = "observationType", required = false) ObservationType[] observationTypes,
            @RequestParam(name = "observationParameter", required = false) ObservationParameter[] observationParameters,
            @RequestParam(name = "organization", required = false) Organization[] organizations,
            @RequestParam(name = "observationScope", required = false) ObservationScope[] observationScopes,
            @RequestParam(name = "observationTerritory", required = false) ObservationTerritory[] observationTerritories,
            @RequestParam(name = "uploadFiles", required = false) MultipartFile[] files,
            @RequestParam(required = false) boolean delAttachedFiles
    ) throws IOException {

        // Если удалили прикреплённые файлы на странице редактирования, то удаляем файлы и ссылки на них на сервере
        if (delAttachedFiles) {
            deleteAttachedFiles(informationResource);
        }

        if (informationResource.getUploadedFiles() != null) {
            transferAttachedFiles(informationResource, inventoryNumber, country, mainOrganization);
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEdit = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        informationResource.setEditedFields(
                inventoryNumber, fullnameCdrom, abbreviationCdrom,
                dateObservationStart, dateObservationEnd,
                briefContent, volume, receivedDate,
                resourceType, language, relatedProject,
                observationMethod, country, mainOrganization,
                duplicate, dateOfEdit, editor
        );

        setInformationResourceManyToManyFields(
                informationResource,
                observationDisciplines, observationTypes, observationParameters,
                organizations, observationScopes, observationTerritories
        );

        List<UploadedFile> uploadedFiles = saveFiles(informationResource, files);
        if (uploadedFiles != null) {
            informationResource.addUploadedFiles(uploadedFiles);
        }

        informationResourceService.save(informationResource);
        return String.format("redirect:/information_resources/%d", informationResource.getId());
    }

    //Вспомогательные функции
    public void setInformationResourceManyToManyFields(
            InformationResource informationResource,
            ObservationDiscipline[] observationDisciplines,
            ObservationType[] observationTypes,
            ObservationParameter[] observationParameters,
            Organization[] organizations,
            ObservationScope[] observationScopes,
            ObservationTerritory[] observationTerritories
    ) {
        Set<ObservationDiscipline> observationDisciplineSet = new HashSet<>();
        if (observationDisciplines != null) {
            for (ObservationDiscipline observationDiscipline : observationDisciplines) {
                if (observationDiscipline != null) {
                    observationDisciplineSet.add(observationDiscipline);
                }
            }
        }
        informationResource.setObservationDisciplines(observationDisciplineSet);

        Set<ObservationType> observationTypeSet = new HashSet<>();
        if (observationTypes != null) {
            for (ObservationType observationType : observationTypes) {
                if (observationType != null) {
                    observationTypeSet.add(observationType);
                }
            }
        }
        informationResource.setObservationTypes(observationTypeSet);

        Set<ObservationParameter> observationParameterSet = new HashSet<>();
        if (observationParameters != null) {
            for (ObservationParameter observationParameter : observationParameters) {
                if (observationParameter != null) {
                    observationParameterSet.add(observationParameter);
                }
            }
        }
        informationResource.setObservationParameters(observationParameterSet);

        Set<Organization> organizationSet = new HashSet<>();
        if (organizations != null) {
            for (Organization organization : organizations) {
                if (organization != null) {
                    organizationSet.add(organization);
                }
            }
        }
        informationResource.setOrganizations(organizationSet);

        Set<ObservationScope> observationScopeSet = new HashSet<>();
        if (observationScopes != null) {
            for (ObservationScope observationScope : observationScopes) {
                if (observationScope != null) {
                    observationScopeSet.add(observationScope);
                }
            }
        }
        informationResource.setObservationScopes(observationScopeSet);

        Set<ObservationTerritory> observationTerritorySet = new HashSet<>();
        if (observationTerritories != null) {
            for (ObservationTerritory observationTerritory : observationTerritories) {
                if (observationTerritory != null) {
                    observationTerritorySet.add(observationTerritory);
                }
            }
        }
        informationResource.setObservationTerritories(observationTerritorySet);
    }

    public List<UploadedFile> saveFiles(InformationResource informationResource, MultipartFile[] files) throws IOException {
        if(files[0].getSize() != 0) {
            List<UploadedFile> uploadedFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null) {
                    String uploadDirPath =  uploadPath + "/" + // добавить год
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

    public void transferAttachedFiles(InformationResource informationResource, String inventoryNumber, Country country, Organization mainOrganization) {
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

    @Transactional
    public void deleteAttachedFiles(InformationResource informationResource) {
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationResource(informationResource);
        for (UploadedFile uploadedFile : uploadedFiles) {
            File file = new File(uploadedFile.getPath());

            if (file.exists()) {
                file.delete();
            }
        }
        uploadedFileRepository.deleteByInformationResource(informationResource);
    }
}