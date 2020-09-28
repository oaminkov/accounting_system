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
    private ProjectTypeService projectTypeService;
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

    @GetMapping("information_products")
    public String viewAllProducts(Model model) {
        List<InformationProduct> listInformationProducts = informationProductService.listAll();
        List<UploadedFile> uploadedFiles = uploadedFileRepository.findAll();

        model.addAttribute("listInformationProducts", listInformationProducts);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "view_information_products";
    }

    @GetMapping("information_products/view/{id}")
    public String showFullInformationProduct(
            @PathVariable("id") InformationProduct informationProduct,
            Model model
    ){
        Set<UploadedFile> uploadedFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

        model.addAttribute("informationProduct", informationProduct);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "view_information_product_full";
    }

    @GetMapping(value="information_products/download/{id}", produces="application/zip")
    public void zipFiles(HttpServletResponse response,
                         @PathVariable("id") InformationProduct informationProduct,
                         @ModelAttribute("uploadedFile") UploadedFile uploadedFile
    ) throws IOException {
        //Устанавливаем заголовки
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"uploadedFiles.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        Set<File> downloadFiles = new HashSet<>();
        Set<UploadedFile> tempFiles = uploadedFileRepository.findByInformationProduct(informationProduct);

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

    @GetMapping("information_products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long idInformationProduct) {
        informationProductService.delete(idInformationProduct);
        return "redirect:/information_products";
    }

    @GetMapping("information_products/add")
    public String showNewInformationProductPage(Model model) {
        List<Country> listCountry = countryService.listAll(); //select стран
        model.addAttribute("listCountry", listCountry);

        List<GeographicalObject> listGeographicalObject = geographicalObjectService.listAll(); //select географического объекта
        model.addAttribute("listGeographicalObject", listGeographicalObject);

        List<Language> listLanguage = languageService.listAll(); //select языка
        model.addAttribute("listLanguage", listLanguage);

        List<ProjectType> listProjectType = projectTypeService.listAll(); //проекты/программы
        model.addAttribute("listProjectOrProgram", listProjectType);

        List<ObservationDiscipline> listObservationDiscipline = observationDisciplineService.listAll();
        model.addAttribute("listObservationDiscipline", listObservationDiscipline);

        List<ObservationScope> listObservationScope = observationScopeService.listAll();
        model.addAttribute("listObservationScope", listObservationScope);

        List<Organization> listOrganization = organizationService.listAll();
        model.addAttribute("listOrganization", listOrganization);

        return "add_information_product";
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

    @PostMapping("information_products/add")
    public String saveInformationProduct(
            @AuthenticationPrincipal User operator,
            @RequestParam Language language,
            @RequestParam ProjectType projectType,
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
            @RequestParam(value = "uploadFiles") MultipartFile[] files
    ) throws IOException {

        LocalDateTime myDateObj = LocalDateTime.now();
        String dateOfEntering = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        InformationProduct informationProduct = new InformationProduct(
                        operator, language, projectType, country, observationMethod,
                        inventoryNumber, fullnameCdrom, abbreviationCdrom, dateObservationStart, dateObservationEnd,
                        briefContent, volume, receivedDate, duplicate, dateOfEntering);

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

                    uploadedFiles.add(new UploadedFile(resultFilename, resultFilepath, informationProduct));
                }
            }
            informationProduct.setUploadedFiles(uploadedFiles);
        }

        informationProductService.save(informationProduct);

        return "redirect:/information_products";
    }

    /*@PostMapping("/information_product/update/{id}")
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