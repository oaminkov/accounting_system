package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "related_project")
public class RelatedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "related_project_generator")
    @SequenceGenerator(name="related_project_generator", sequenceName = "related_project_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="name_rus")
    private String nameRus;

    @Column(nullable = false)
    private String abbreviation;

    @Column(name="abbreviation_rus")
    private String abbreviationRus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "id_project_type")
    private ProjectType projectType;

    @OneToMany(mappedBy = "relatedProject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    public RelatedProject() { }

    public RelatedProject(String name, String nameRus, String abbreviation, String abbreviationRus, ProjectType projectType) {
        this.name = name;
        this.nameRus = nameRus;
        this.abbreviation = abbreviation;
        this.abbreviationRus = abbreviationRus;
        this.projectType = projectType;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNameRus() {
        return nameRus;
    }
    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviationRus() {
        return abbreviationRus;
    }
    public void setAbbreviationRus(String abbreviationRus) {
        this.abbreviationRus = abbreviationRus;
    }

    public ProjectType getProjectType() {
        return projectType;
    }
    public void setProjectType(String type) {
        this.projectType = projectType;
    }

    public List<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(List<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}