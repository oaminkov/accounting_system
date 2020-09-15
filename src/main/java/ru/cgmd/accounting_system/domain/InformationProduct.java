package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "information_product")
public class InformationProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_information_product")
    private Long idInformationProduct;

    @ManyToOne(fetch = FetchType.LAZY) //проект или программа
    @JoinColumn (name = "id_project_or_program", nullable = false)
    private ProjectOrProgram projectOrProgram;

    @ManyToOne(fetch = FetchType.LAZY) //страна
    @JoinColumn (name = "id_country", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY) //язык
    @JoinColumn (name = "id_language", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY) //дисциплина наблюдений
    @JoinColumn (name = "id_observation_discipline", nullable = false)
    private ObservationDiscipline observationDiscipline;

    @ManyToOne(fetch = FetchType.LAZY) //вид наблюдений
    @JoinColumn (name = "id_observation_type", nullable = false)
    private ObservationType observationType;

    @ManyToOne(fetch = FetchType.LAZY) //сфера наблюдений
    @JoinColumn (name = "id_observation_scope", nullable = false)
    private ObservationScope observationScope;

    @ManyToOne(fetch = FetchType.LAZY) //геогр объект
    @JoinColumn (name = "id_geographical_object", nullable = false)
    private GeographicalObject geographicalObject;

    @ManyToOne(fetch = FetchType.LAZY) //организация
    @JoinColumn (name = "id_organization", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY) //оператор
    @JoinColumn (name = "id_usr", nullable = false)
    private User user;

    @Column(nullable = false, name="inventory_number", unique = true)
    private String inventoryNumber;

    @Column(nullable = false, name="fullname_cdrom")
    private String fullnameCdrom;

    @Column(nullable = false, name="abbreviation_cdrom")
    private String abbreviationCdrom;

    @Column(nullable = false, name="date_observation_start")
    private String dateObservationStart;

    @Column(nullable = false, name="date_observation_end")
    private String dateObservationEnd;

    @Column(nullable = false, name="volume")
    private String volume;

    @Column(nullable = false, name="received_date")
    private String receivedDate;

    @Column(length = 2048, nullable = false, name="brief_content")
    private String briefContent;

    @Column(nullable = false, name="duplicate")
    private boolean duplicate;

    @Column(nullable = false, name="date_of_entering")
    private String dateOfEntering;

    @Column(name="date_of_edit")
    private String dateOfEdit;

    @ManyToOne(fetch = FetchType.LAZY) //оператор
    @JoinColumn (name = "id_editor")
    private User editor;

    @OneToMany(mappedBy = "informationProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFile> uploadedFiles;

    public InformationProduct() { }

    public InformationProduct(ProjectOrProgram projectOrProgram,
                              Country country,
                              Language language,
                              ObservationDiscipline observationDiscipline,
                              ObservationType observationType,
                              ObservationScope observationScope,
                              GeographicalObject geographicalObject,
                              Organization organization,
                              String inventoryNumber,
                              String fullnameCdrom,
                              String abbreviationCdrom,
                              String dateObservationStart,
                              String dateObservationEnd,
                              String volume,
                              String receivedDate,
                              String briefContent,
                              boolean duplicate
    ) {
        this.projectOrProgram = projectOrProgram;
        this.country = country;
        this.language = language;
        this.observationDiscipline = observationDiscipline;
        this.observationType = observationType;
        this.observationScope = observationScope;
        this.geographicalObject = geographicalObject;
        this.organization = organization;
        this.inventoryNumber = inventoryNumber;
        this.fullnameCdrom = fullnameCdrom;
        this.abbreviationCdrom = abbreviationCdrom;
        this.dateObservationStart = dateObservationStart;
        this.dateObservationEnd = dateObservationEnd;
        this.volume = volume;
        this.receivedDate = receivedDate;
        this.briefContent = briefContent;
        this.duplicate = duplicate;
    }

    public void addUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles.addAll(uploadedFiles);
    }

    public Long getIdInformationProduct() {
        return idInformationProduct;
    }
    public void setIdInformationProduct(Long idInformationProduct) {
        this.idInformationProduct = idInformationProduct;
    }

    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }

    public ObservationDiscipline getObservationDiscipline() {
        return observationDiscipline;
    }
    public void setObservationDiscipline(ObservationDiscipline observationDiscipline) {
        this.observationDiscipline = observationDiscipline;
    }

    public ObservationType getObservationType() {
        return observationType;
    }
    public void setObservationType(ObservationType observationType) {
        this.observationType = observationType;
    }

    public ObservationScope getObservationScope() {
        return observationScope;
    }
    public void setObservationScope(ObservationScope observationScope) {
        this.observationScope = observationScope;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ProjectOrProgram getProjectOrProgram() {
        return projectOrProgram;
    }
    public void setProjectOrProgram(ProjectOrProgram projectOrProgram) {
        this.projectOrProgram = projectOrProgram;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public GeographicalObject getGeographicalObject() {
        return geographicalObject;
    }
    public void setGeographicalObject(GeographicalObject geographicalObject) {
        this.geographicalObject = geographicalObject;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }
    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getFullnameCdrom() {
        return fullnameCdrom;
    }
    public void setFullnameCdrom(String fullnameCdrom) {
        this.fullnameCdrom = fullnameCdrom;
    }

    public String getAbbreviationCdrom() {
        return abbreviationCdrom;
    }
    public void setAbbreviationCdrom(String abbreviationCdrom) {
        this.abbreviationCdrom = abbreviationCdrom;
    }

    public String getDateObservationStart() {
        return dateObservationStart;
    }
    public void setDateObservationStart(String dateObservationStart) {
        this.dateObservationStart = dateObservationStart;
    }

    public String getDateObservationEnd() {
        return dateObservationEnd;
    }
    public void setDateObservationEnd(String dateObservationEnd) {
        this.dateObservationEnd = dateObservationEnd;
    }

    public String getBriefContent() {
        return briefContent;
    }
    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getDateOfEntering() {
        return dateOfEntering;
    }
    public void setDateOfEntering(String dateOfEntering) {
        this.dateOfEntering = dateOfEntering;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }
    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public String getDateOfEdit() {
        return dateOfEdit;
    }
    public void setDateOfEdit(String dateOfEdit) {
        this.dateOfEdit = dateOfEdit;
    }

    public User getEditor() {
        return editor;
    }
    public void setEditor(User editor) {
        this.editor = editor;
    }
}