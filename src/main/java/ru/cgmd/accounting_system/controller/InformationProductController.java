package ru.cgmd.accounting_system.controller;

import ru.cgmd.accounting_system.domain.*;
import ru.cgmd.accounting_system.repos.ObservationTypeRepository;
import ru.cgmd.accounting_system.repos.UploadedFileRepository;
import ru.cgmd.accounting_system.service.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class InformationProductController {
    @Value("${upload.path}")
    private String uploadPath;
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
    @Autowired
    private InformationProductService informationProductService;
    @Autowired
    private UploadedFileRepository uploadedFileRepository;
    @Autowired
    private ObservationTypeRepository observationTypeRepository;

    //View all information products
    @GetMapping("/view_informationproduct")
    public String viewAllProducts(Model model) {
        List<InformationProduct> listInformationProducts = informationProductService.listAll();
        model.addAttribute("listInformationProducts", listInformationProducts);

        List<UploadedFile> uploadedFiles = uploadedFileRepository.findAll();
        model.addAttribute("uploadedFiles", uploadedFiles);

        return "view_informationproduct";
    }

    @GetMapping("/add_informationproduct")
    public String showNewInformationProductPage(Model model) {
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

        List<ObservationScope> listObservationScope = observationScopeService.listAll();
        model.addAttribute("listObservationScope", listObservationScope);

        List<Organization> listOrganization = organizationService.listAll();
        model.addAttribute("listOrganization", listOrganization);

        return "add_informationproduct";
    }

    // DELETE PRODUCT
    @GetMapping("information_product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long idInformationProduct) {
        informationProductService.delete(idInformationProduct);
        return "redirect:/view_informationproduct";
    }

    // DELETE FILES
    @Transactional
    @PostMapping("information_product/delete_files/{id}")
    public String deleteFiles(@PathVariable("id") InformationProduct informationProduct, Model model) {
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        for (UploadedFile uploadedFile : uploadedFiles) {
            File file = new File(uploadedFile.getPathUploadedFile());

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
    }

    // VIEW PRODUCT
    @GetMapping("/information_product/view/{id}")
    public String showFullInformationProduct(
            @PathVariable("id") InformationProduct informationProduct,
            Model model
    ){
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        model.addAttribute("informationProduct", informationProduct);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "/full_view_informationproduct";
    }

    // EDIT PRODUCT
    @GetMapping("/information_product/edit/{id}")
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

        return "/update_informationproduct";
    }

    @GetMapping(value="/information_product/download/{id}", produces="application/zip")
    public void zipFiles(HttpServletResponse response,
                         @PathVariable("id") InformationProduct informationProduct,
                         @ModelAttribute("uploadedFile") UploadedFile uploadedFile
    ) throws IOException {
        //setting headers
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"uploadedFiles.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        Set<File> downloadFiles = new HashSet<>();
        Set<UploadedFile> tempFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        for (UploadedFile file : tempFiles) {
            File downloadFile = new File(file.getPathUploadedFile());
            downloadFiles.add(downloadFile);

            zipOutputStream.putNextEntry(new ZipEntry(downloadFile.getName()));
            FileInputStream fileInputStream = new FileInputStream(downloadFile);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

    @PostMapping("/add_informationproduct")
    public String saveInformationProduct(
            @AuthenticationPrincipal User author,
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

        InformationProduct informationProduct = new InformationProduct(
                projectOrProgram, country, language, observationDiscipline, observationType,
                observationScope, geographicalObject, organization, inventoryNumber, fullnameCdrom, abbreviationCdrom,
                dateObservationStart, dateObservationEnd, volume, receivedDate, briefContent, dup);

        List<UploadedFile> uploadedFiles = new ArrayList<>();

        if(files[0].getSize() != 0) {
            for (MultipartFile file : files) {
                if (file != null) {
                    String uploadDirPath = uploadPath + "/" + organization.getIdOrganization() + "/" + inventoryNumber;
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
            informationProduct.setUploadedFiles(uploadedFiles);
        }

        informationProduct.setUser(author);

        LocalDateTime myDateObj = LocalDateTime.now();
        String formattedDate = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        informationProduct.setDateOfEntering(formattedDate);
        informationProductService.save(informationProduct);

        return "redirect:/view_informationproduct";
    }

    @PostMapping("/information_product/update/{id}")
    public String updateInformationProduct(
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
            String uploadDirPath = uploadPath + "/" + organization.getIdOrganization() + "/" + inventoryNumber;
            File uploadDir = new File(uploadDirPath);


            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            Set<UploadedFile> uploadedFilesTemp = uploadedFileRepository.findByInformationProduct(informationProduct);

            for (UploadedFile uploadedFile : uploadedFilesTemp) {
                File file = new File(uploadedFile.getPathUploadedFile());
                String filename = file.getName();
                String filepath = uploadDirPath + "/" + filename;

                if (file.exists()) {
                    file.renameTo(new File(filepath));
                }
                uploadedFile.setPathUploadedFile(filepath);
                uploadedFileRepository.save(uploadedFile);
            }
            String oldUploadDirPath = uploadPath + "/" + organizationTemp.getIdOrganization() + "/" + inventoryNumberTemp;
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
                    String uploadDirPath = uploadPath + "/" + organization.getIdOrganization() + "/" + inventoryNumber;
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

        return "redirect:/information_product/edit/" + informationProduct.getIdInformationProduct();
    }
}