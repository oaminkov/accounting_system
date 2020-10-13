package ru.cgmd.accounting_system.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repos.UploadedFileRepository;
import ru.cgmd.accounting_system.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class InformationResourceGetController {
    @Autowired
    private InformationResourceService informationResourceService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private RelatedProjectService relatedProjectService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ObservationMethodService observationMethodService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationScopeService observationScopeService;
    @Autowired
    private ObservationTerritoryService observationTerritoryService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    public void selectDataFromDbToModel(Model model) {
        List<Language> languages = languageService.listAll();
        List<RelatedProject> relatedProjects = relatedProjectService.listAll();
        List<Country> countries = countryService.listAll();
        List<ObservationMethod> observationMethods = observationMethodService.listAll();

        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        List<ObservationScope> observationScopes = observationScopeService.listAll();
        List<ObservationTerritory> observationTerritories = observationTerritoryService.listAll();
        List<Organization> organizations = organizationService.listAll();

        model.addAttribute("languages", languages);
        model.addAttribute("relatedProjects", relatedProjects);
        model.addAttribute("countries", countries);
        model.addAttribute("observationMethods", observationMethods);

        model.addAttribute("observationDisciplines", observationDisciplines);
        model.addAttribute("observationScopes", observationScopes);
        model.addAttribute("observationTerritories", observationTerritories);
        model.addAttribute("organizations", organizations);
    }

    @GetMapping("information_resources")
    public String viewAllInformationResources(Model model) {
        List<InformationResource> informationResources = informationResourceService.listAll();
        List<UploadedFile> uploadedFiles = uploadedFileRepository.findAll();

        model.addAttribute("informationResources", informationResources);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "view_information_resources";
    }

    @GetMapping("information_resources/{id}")
    public String viewInformationResourceFull(
            @PathVariable("id") InformationResource informationResource,
            Model model){
        model.addAttribute("informationResource", informationResource);
        return "view_information_resource_full";
    }

    @GetMapping("information_resources/add")
    public String showNewInformationProductPage(Model model) {
        selectDataFromDbToModel(model);

        return "add_information_resource";
    }

    @GetMapping("information_resources/edit/{id}")
    public String showEditInformationProductPage(
            @PathVariable("id") InformationResource informationResource,
            Model model){
        selectDataFromDbToModel(model);
        model.addAttribute("informationResource", informationResource);

        return "edit_information_resource";
    }

    @GetMapping(value="information_resources/download/{id}", produces="application/zip")
    public void zipFiles(HttpServletResponse response,
                         @PathVariable("id") InformationResource informationResource,
                         @ModelAttribute UploadedFile uploadedFile
    ) throws IOException {
        //Устанавливаем заголовки
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"uploadedFiles.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        Set<File> downloadFiles = new HashSet<>();
        Set<UploadedFile> tempFiles = uploadedFileRepository.findByInformationResource(informationResource);

        for (UploadedFile file : tempFiles) {
            File downloadFile = new File(file.getPath());
            downloadFiles.add(downloadFile);

            zipOutputStream.putNextEntry(new ZipEntry(downloadFile.getName()));
            FileInputStream fileInputStream = new FileInputStream(downloadFile);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

    @GetMapping("information_resources/delete/{id}")
    public String deleteInformationResource(@PathVariable("id") InformationResource informationResource) {
        informationResourceService.delete(informationResource);
        return "redirect:/information_resources";
    }
}