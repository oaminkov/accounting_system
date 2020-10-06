package ru.cgmd.accounting_system.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repos.UploadedFileRepository;
import ru.cgmd.accounting_system.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class InformationResourceController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GeographicalObjectService geographicalObjectService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private RelatedProjectService relatedProjectService;
    @Autowired
    private ObservationDisciplineService observationDisciplineService;
    @Autowired
    private ObservationScopeService observationScopeService;
    @Autowired
    private ObservationMethodService observationMethodService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private InformationResourceService informationResourceService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

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
            Model model
    ){
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationResource(informationResource);

        model.addAttribute("informationResource", informationResource);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "view_information_resource_full";
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
    public String deleteInformationResource(@PathVariable("id") Long id) {
        informationResourceService.delete(id);
        return "redirect:/information_resources";
    }

    @GetMapping("information_resources/add")
    public String showNewInformationProductPage(Model model) {
        List<Country> countries = countryService.listAll();
        List<GeographicalObject> geographicalObjects = geographicalObjectService.listAll();
        List<Language> languages = languageService.listAll();
        List<RelatedProject> relatedProjects = relatedProjectService.listAll();
        List<ObservationDiscipline> observationDisciplines = observationDisciplineService.listAll();
        List<ObservationScope> observationScopes = observationScopeService.listAll();
        List<Organization> organizations = organizationService.listAll();
        List<ObservationMethod> observationMethods = observationMethodService.listAll();

        model.addAttribute("countries", countries);
        model.addAttribute("geographicalObjects", geographicalObjects);
        model.addAttribute("languages", languages);
        model.addAttribute("relatedProjects", relatedProjects);
        model.addAttribute("observationDisciplines", observationDisciplines);
        model.addAttribute("observationScopes", observationScopes);
        model.addAttribute("organizations", organizations);
        model.addAttribute("observationMethods", observationMethods);

        return "add_information_resource";
    }

    /*@GetMapping("/information_product/edit/{id}")
    public String showUpdateInformationProduct(@PathVariable("id") InformationProduct informationProduct,
                                               Model model){
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        List<Country> listCountry = countryService.listAll(); //select стран
        model.addAttribute("listCountry", listCountry);

        List<GeographicalObject> listGeographicalObject = geographicalObjectService.listAll(); //select географического объекта
        model.addAttribute("listGeographicalObject", listGeographicalObject);

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

        return "update_informationproduct";
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("information_resources/add")
    public String saveInformationResource(
            @AuthenticationPrincipal User operator,
            @RequestParam Language language,
            @RequestParam RelatedProject relatedProject,
            @RequestParam Country country,
            @RequestParam ObservationMethod observationMethod,

            @RequestParam(value = "observationDiscipline") ObservationDiscipline[] observationDisciplines,
            //@RequestParam(value = "observationType") ObservationType[] observationTypes,
            //@RequestParam(value = "observationParameter") ObservationParameter[] observationParameters,

            @RequestParam String inventoryNumber,
            @RequestParam String fullnameCdrom,
            @RequestParam String abbreviationCdrom,
            @RequestParam String dateObservationStart,
            @RequestParam String dateObservationEnd,
            @RequestParam String briefContent,
            @RequestParam String volume,
            @RequestParam String receivedDate,
            @RequestParam(defaultValue = "0") boolean duplicate,
            @RequestParam(value = "uploadFiles") MultipartFile[] files
    ) throws IOException {

        for (ObservationDiscipline observationDiscipline : observationDisciplines) {
            System.out.println(observationDiscipline.getName());
        }

        System.out.println("---END---");

        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEntering = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        /*InformationResource informationResource = new InformationResource(
                        operator, language, relatedProject, country, observationMethod,
                        inventoryNumber, fullnameCdrom, abbreviationCdrom, dateObservationStart, dateObservationEnd,
                        briefContent, volume, receivedDate, duplicate, dateOfEntering);*/

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

                    //file.transferTo(new File(resultFilepath));

                    //uploadedFiles.add(new UploadedFile(resultFilename, resultFilepath, informationResource));
                }
            }
            //informationResource.setUploadedFiles(uploadedFiles);
        }

        //informationResourceService.save(informationResource);

        return "redirect:/information_resources";
    }

    /*@PostMapping("/information_resources/update/{id}")
    public String updateInformationResource(
            @PathVariable("id") InformationProduct informationProduct,
            @AuthenticationPrincipal User editor,
            @RequestParam ProjectOrProgram projectOrProgram,
            @RequestParam Country country,
            @RequestParam Language language,
            @RequestParam ObservationDiscipline observationDiscipline,
            @RequestParam ObservationType observationType,
            @RequestParam ObservationScope observationScope,
            @RequestParam GeographicalObject geographicalObject,
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
            String uploadDirPath = uploadPath + "/" + organization.getId() + "/" + inventoryNumber;
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
        informationProduct.setGeographicalObject(geographicalObject);
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

        List<GeographicalObject> listGeographicalObject = geographicalObjectService.listAll(); //select географического объекта
        model.addAttribute("listGeographicalObject", listGeographicalObject);

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