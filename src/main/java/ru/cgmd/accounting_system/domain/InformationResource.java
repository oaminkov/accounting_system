package ru.cgmd.accounting_system.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "information_resource")
public class InformationResource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "information_resource_generator")
    @SequenceGenerator(name = "information_resource_generator", sequenceName = "information_resource_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false, name = "inventory_number", unique = true)
    private String inventoryNumber;

    @Column(nullable = false, name = "fullname_cdrom")
    private String fullnameCdrom;

    @Column(nullable = false, name = "abbreviation_cdrom")
    private String abbreviationCdrom;

    @Column(nullable = false, name = "date_observation_start")
    private String dateObservationStart;

    @Column(nullable = false, name = "date_observation_end")
    private String dateObservationEnd;

    @Column(length = 2048, nullable = false, name = "brief_content")
    private String briefContent;

    @Column(nullable = false, name = "volume")
    private String volume;

    @Column(nullable = false, name = "received_date")
    private String receivedDate;

    @Column(nullable = false, name = "duplicate")
    private Boolean duplicate;

    @Column(nullable = false, name = "date_of_entering")
    private String dateOfEntering;

    @Column(name = "date_of_edit")
    private String dateOfEdit;

    @ManyToOne(fetch = FetchType.LAZY) //тип ресурса
    @JoinColumn(name = "id_resource_type", nullable = false)
    @ToString.Exclude
    private ResourceType resourceType;

    @ManyToOne(fetch = FetchType.LAZY) //язык
    @JoinColumn(name = "id_language", nullable = false)
    @ToString.Exclude
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY) //связанный проект (проект или программа)
    @JoinColumn(name = "id_related_project", nullable = false)
    @ToString.Exclude
    private RelatedProject relatedProject;

    @ManyToOne(fetch = FetchType.LAZY) //метод наблюдений
    @JoinColumn(name = "id_observation_method", nullable = false)
    @ToString.Exclude
    private ObservationMethod observationMethod;

    @ManyToOne(fetch = FetchType.LAZY) //страна
    @JoinColumn(name = "id_country", nullable = false)
    @ToString.Exclude
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY) //главная организация
    @JoinColumn(name = "id_main_organization", nullable = false)
    @ToString.Exclude
    private Organization mainOrganization;

    @ManyToOne(fetch = FetchType.LAZY) //оператор
    @JoinColumn(name = "id_operator", nullable = false)
    @ToString.Exclude
    private User operator;

    @ManyToOne(fetch = FetchType.LAZY) //редактор
    @JoinColumn(name = "id_editor")
    @ToString.Exclude
    private User editor;

    @OneToMany(mappedBy = "informationResource", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UploadedFile> uploadedFiles;

    @ManyToMany //дисциплины наблюдений
    @JoinTable(
            name = "infres_observdiscipl",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_observation_discipline")}
    )
    @ToString.Exclude
    private Set<ObservationDiscipline> observationDisciplines = new HashSet<>();

    @ManyToMany //виды наблюдений
    @JoinTable(
            name = "infres_observtype",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_observation_type")}
    )
    @ToString.Exclude
    private Set<ObservationType> observationTypes = new HashSet<>();

    @ManyToMany //параметры наблюдений
    @JoinTable(
            name = "infres_observparam",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_observation_parameter")}
    )
    @ToString.Exclude
    private Set<ObservationParameter> observationParameters = new HashSet<>();

    @ManyToMany //сферы наблюдений
    @JoinTable(
            name = "infres_observscope",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_observation_scope")}
    )
    @ToString.Exclude
    private Set<ObservationScope> observationScopes = new HashSet<>();


    @ManyToMany //территории наблюдений
    @JoinTable(
            name = "infres_observterritory",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_observation_territory")}
    )
    @ToString.Exclude
    private Set<ObservationTerritory> observationTerritories = new HashSet<>();

    @ManyToMany //организации
    @JoinTable(
            name = "infres_organization",
            joinColumns = {@JoinColumn(name = "id_information_resource")},
            inverseJoinColumns = {@JoinColumn(name = "id_organization")}
    )
    @ToString.Exclude
    private Set<Organization> organizations = new HashSet<>();

    public InformationResource(
            String inventoryNumber,
            String fullnameCdrom,
            String abbreviationCdrom,
            String dateObservationStart,
            String dateObservationEnd,
            String briefContent,
            String volume,
            String receivedDate,
            ResourceType resourceType,
            Language language,
            RelatedProject relatedProject,
            ObservationMethod observationMethod,
            Country country,
            Organization mainOrganization,
            Boolean duplicate,
            String dateOfEntering,
            User operator
    ) {
        this.inventoryNumber = inventoryNumber;
        this.fullnameCdrom = fullnameCdrom;
        this.abbreviationCdrom = abbreviationCdrom;
        this.dateObservationStart = dateObservationStart;
        this.dateObservationEnd = dateObservationEnd;
        this.briefContent = briefContent;
        this.volume = volume;
        this.receivedDate = receivedDate;
        this.resourceType = resourceType;
        this.language = language;
        this.relatedProject = relatedProject;
        this.observationMethod = observationMethod;
        this.country = country;
        this.mainOrganization = mainOrganization;
        this.duplicate = duplicate;
        this.dateOfEntering = dateOfEntering;
        this.operator = operator;
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
            ResourceType resourceType,
            Language language,
            RelatedProject relatedProject,
            ObservationMethod observationMethod,
            Country country,
            Organization mainOrganization,
            Boolean duplicate,
            String dateOfEdit,
            User editor
    ) {
        this.inventoryNumber = inventoryNumber;
        this.fullnameCdrom = fullnameCdrom;
        this.abbreviationCdrom = abbreviationCdrom;
        this.dateObservationStart = dateObservationStart;
        this.dateObservationEnd = dateObservationEnd;
        this.briefContent = briefContent;
        this.volume = volume;
        this.receivedDate = receivedDate;
        this.resourceType = resourceType;
        this.language = language;
        this.relatedProject = relatedProject;
        this.observationMethod = observationMethod;
        this.country = country;
        this.mainOrganization = mainOrganization;
        this.duplicate = duplicate;
        this.dateOfEdit = dateOfEdit;
        this.editor = editor;
    }

    public void addUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles.addAll(uploadedFiles);
    }
}