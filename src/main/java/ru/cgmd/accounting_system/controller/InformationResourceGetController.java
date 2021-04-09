package ru.cgmd.accounting_system.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repo.UploadedFileRepository;
import ru.cgmd.accounting_system.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("information_resources")
public class InformationResourceGetController {
    private final InformationResourceService informationResourceService;
    private final SourceService sourceService;
    private final RelatedProjectService relatedProjectService;
    private final LanguageService languageService;
    private final CountryService countryService;
    private final ObservationMethodService observationMethodService;
    private final ObservationDisciplineService observationDisciplineService;
    private final ObservationScopeService observationScopeService;
    private final ObservationTerritoryService observationTerritoryService;
    private final OrganizationService organizationService;
    private final UploadedFileRepository uploadedFileRepository;

    public InformationResourceGetController(
            InformationResourceService informationResourceService,
            SourceService sourceService,
            RelatedProjectService relatedProjectService,
            LanguageService languageService,
            CountryService countryService,
            ObservationMethodService observationMethodService,
            ObservationDisciplineService observationDisciplineService,
            ObservationScopeService observationScopeService,
            ObservationTerritoryService observationTerritoryService,
            OrganizationService organizationService,
            UploadedFileRepository uploadedFileRepository
    ) {
        this.informationResourceService = informationResourceService;
        this.sourceService = sourceService;
        this.relatedProjectService = relatedProjectService;
        this.languageService = languageService;
        this.countryService = countryService;
        this.observationMethodService = observationMethodService;
        this.observationDisciplineService = observationDisciplineService;
        this.observationScopeService = observationScopeService;
        this.observationTerritoryService = observationTerritoryService;
        this.organizationService = organizationService;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    public void selectDataFromDbToModel(Model model) {
        List<Source> sources = sourceService.listAll();
        List<RelatedProject> relatedProjects = relatedProjectService.listAll();
        List<Language> languages = languageService.listAll();
        List<Country> countries = countryService.listAll();
        List<ObservationMethod> observationMethods = observationMethodService.listAll();

        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        List<ObservationScope> observationScopes = observationScopeService.listAll();
        List<ObservationTerritory> observationTerritories = observationTerritoryService.listAll();
        List<Organization> organizations = organizationService.listAll();

        model.addAttribute("sources", sources);
        model.addAttribute("relatedProjects", relatedProjects);
        model.addAttribute("languages", languages);
        model.addAttribute("countries", countries);
        model.addAttribute("observationMethods", observationMethods);

        model.addAttribute("observationDisciplines", observationDisciplines);
        model.addAttribute("observationScopes", observationScopes);
        model.addAttribute("observationTerritories", observationTerritories);
        model.addAttribute("organizations", organizations);
    }

    @GetMapping
    public String viewAllInformationResources(Model model) {
        List<InformationResource> informationResources = informationResourceService.listAll();
        model.addAttribute("informationResources", informationResources);
        return "view_information_resources";
    }

    @GetMapping("add")
    public String showNewInformationProductPage(Model model) {
        selectDataFromDbToModel(model);
        return "add_information_resource";
    }

    @GetMapping("{id}")
    public String viewInformationResourceFull(
            @PathVariable("id") InformationResource informationResource,
            Model model
    ) {
        model.addAttribute("informationResource", informationResource);
        return "view_information_resource_full";
    }

    @GetMapping("edit/{id}")
    public String showEditInformationProductPage(
            @PathVariable("id") InformationResource informationResource,
            Model model,
            HttpServletRequest request
    ){
        selectDataFromDbToModel(model);

        model.addAttribute("informationResource", informationResource);
        model.addAttribute("backAddress", request.getHeader("referer"));
        return "edit_information_resource";
    }

    @GetMapping("delete/{id}")
    public String deleteInformationResource(@PathVariable("id") InformationResource informationResource) {
        informationResourceService.delete(informationResource);
        return "redirect:/information_resources";
    }

    @GetMapping(value="download/{id}", produces="application/zip")
    public void zipFiles(HttpServletResponse response,
                         @PathVariable("id") InformationResource informationResource) throws IOException {
        //Устанавливаем заголовки
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"uploadedFiles.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationResource(informationResource);

        for (UploadedFile uploadedFile : uploadedFiles) {
            File downloadFile = new File(uploadedFile.getPath());

            zipOutputStream.putNextEntry(new ZipEntry(downloadFile.getName()));
            FileInputStream fileInputStream = new FileInputStream(downloadFile);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }
}