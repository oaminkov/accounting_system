package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "information_resource")
public class InformationResource {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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

    @Column(length = 2048, nullable = false, name="brief_content")
    private String briefContent;

    @Column(nullable = false, name="volume")
    private String volume;

    @Column(nullable = false, name="received_date")
    private String receivedDate;

    @Column(nullable = false, name="duplicate")
    private boolean duplicate;

    @Column(nullable = false, name="date_of_entering")
    private String dateOfEntering;

    @Column(name="date_of_edit")
    private String dateOfEdit;

    @ManyToOne(fetch = FetchType.LAZY) //язык
    @JoinColumn (name = "id_language", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY) //связанный проект (проект или программа)
    @JoinColumn (name = "id_related_project", nullable = false)
    private RelatedProject relatedProject;

    @ManyToOne(fetch = FetchType.LAZY) //страна
    @JoinColumn (name = "id_country", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY) //главная организация
    @JoinColumn (name = "id_main_organization", nullable = false)
    private Organization mainOrganization;

    @ManyToOne(fetch = FetchType.LAZY) //метод наблюдений
    @JoinColumn (name = "id_observation_method", nullable = false)
    private ObservationMethod observationMethod;

    @ManyToOne(fetch = FetchType.LAZY) //оператор
    @JoinColumn (name = "id_operator", nullable = false)
    private User operator;

    @ManyToOne(fetch = FetchType.LAZY) //редактор
    @JoinColumn (name = "id_editor")
    private User editor;

    @OneToMany(mappedBy = "informationResource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFile> uploadedFiles;

    @ManyToMany //дисциплины наблюдений
    @JoinTable(
            name = "infres_observdiscipl",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_observation_discipline") }
    )
    private Set<ObservationDiscipline> observationDisciplines = new HashSet<>();

    @ManyToMany //виды наблюдений
    @JoinTable(
            name = "infres_observtype",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_observation_type") }
    )
    private Set<ObservationType> observationTypes = new HashSet<>();

    @ManyToMany //параметры наблюдений
    @JoinTable(
            name = "infres_observparam",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_observation_parameter") }
    )
    private Set<ObservationParameter> observationParameters = new HashSet<>();

    @ManyToMany //сферы наблюдений
    @JoinTable(
            name = "infres_observscope",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_observation_scope") }
    )
    private Set<ObservationScope> observationScopes = new HashSet<>();


    @ManyToMany //территории наблюдений
    @JoinTable(
            name = "infres_observterritory",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_observation_territory") }
    )
    private Set<ObservationTerritory> observationTerritories = new HashSet<>();

    @ManyToMany //организации
    @JoinTable(
            name = "infres_organization",
            joinColumns = { @JoinColumn(name = "id_information_resource") },
            inverseJoinColumns = { @JoinColumn(name = "id_organization") }
    )
    private Set<Organization> organizations = new HashSet<>();

    public InformationResource() { }

    public InformationResource(
            String inventoryNumber,
            String fullnameCdrom,
            String abbreviationCdrom,
            String dateObservationStart,
            String dateObservationEnd,
            String briefContent,
            String volume,
            String receivedDate,
            Language language,
            RelatedProject relatedProject,
            Country country,
            Organization mainOrganization,
            ObservationMethod observationMethod,
            boolean duplicate,
            User operator,
            String dateOfEntering
    ) {
        this.inventoryNumber = inventoryNumber;
        this.fullnameCdrom = fullnameCdrom;
        this.abbreviationCdrom = abbreviationCdrom;
        this.dateObservationStart = dateObservationStart;
        this.dateObservationEnd = dateObservationEnd;
        this.briefContent = briefContent;
        this.volume = volume;
        this.receivedDate = receivedDate;
        this.language = language;
        this.relatedProject = relatedProject;
        this.country = country;
        this.mainOrganization = mainOrganization;
        this.observationMethod = observationMethod;
        this.duplicate = duplicate;
        this.operator = operator;
        this.dateOfEntering = dateOfEntering;
    }

    public void setEditedFields(
            String inventoryNumber,
            String fullnameCdrom,
            String abbreviationCdrom,
            String dateObservationStart,
            String dateObservationEnd,
            String briefContent,
            String volume,
            String receivedDate,
            Language language,
            RelatedProject relatedProject,
            Country country,
            Organization mainOrganization,
            ObservationMethod observationMethod,
            boolean duplicate,
            User editor,
            String dateOfEdit
    ) {
        this.inventoryNumber = inventoryNumber;
        this.fullnameCdrom = fullnameCdrom;
        this.abbreviationCdrom = abbreviationCdrom;
        this.dateObservationStart = dateObservationStart;
        this.dateObservationEnd = dateObservationEnd;
        this.briefContent = briefContent;
        this.volume = volume;
        this.receivedDate = receivedDate;
        this.language = language;
        this.relatedProject = relatedProject;
        this.country = country;
        this.mainOrganization = mainOrganization;
        this.observationMethod = observationMethod;
        this.duplicate = duplicate;
        this.editor = editor;
        this.dateOfEdit = dateOfEdit;
    }

    public void addUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles.addAll(uploadedFiles);
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }
    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getBriefContent() {
        return briefContent;
    }
    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public boolean isDuplicate() {
        return duplicate;
    }
    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }

    public RelatedProject getRelatedProject() {
        return relatedProject;
    }
    public void setRelatedProject(RelatedProject relatedProject) {
        this.relatedProject = relatedProject;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Organization getMainOrganization() {
        return mainOrganization;
    }
    public void setMainOrganization(Organization mainOrganization) {
        this.mainOrganization = mainOrganization;
    }

    public ObservationMethod getObservationMethod() {
        return observationMethod;
    }
    public void setObservationMethod(ObservationMethod observationMethod) {
        this.observationMethod = observationMethod;
    }

    public User getOperator() {
        return operator;
    }
    public void setOperator(User user) {
        this.operator = user;
    }

    public String getDateOfEntering() {
        return dateOfEntering;
    }
    public void setDateOfEntering(String dateOfEntering) {
        this.dateOfEntering = dateOfEntering;
    }

    public User getEditor() {
        return editor;
    }
    public void setEditor(User editor) {
        this.editor = editor;
    }

    public String getDateOfEdit() {
        return dateOfEdit;
    }
    public void setDateOfEdit(String dateOfEdit) {
        this.dateOfEdit = dateOfEdit;
    }

    public Set<ObservationDiscipline> getObservationDisciplines() {
        return observationDisciplines;
    }
    public void setObservationDisciplines(Set<ObservationDiscipline> observationDisciplines) {
        this.observationDisciplines = observationDisciplines;
    }

    public Set<ObservationType> getObservationTypes() {
        return observationTypes;
    }
    public void setObservationTypes(Set<ObservationType> observationTypes) {
        this.observationTypes = observationTypes;
    }

    public Set<ObservationParameter> getObservationParameters() {
        return observationParameters;
    }
    public void setObservationParameters(Set<ObservationParameter> observationParameters) {
        this.observationParameters = observationParameters;
    }

    public Set<ObservationScope> getObservationScopes() {
        return observationScopes;
    }
    public void setObservationScopes(Set<ObservationScope> observationScopes) {
        this.observationScopes = observationScopes;
    }

    public Set<ObservationTerritory> getObservationTerritories() {
        return observationTerritories;
    }
    public void setObservationTerritories(Set<ObservationTerritory> observationTerritories) {
        this.observationTerritories = observationTerritories;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }
    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }
}