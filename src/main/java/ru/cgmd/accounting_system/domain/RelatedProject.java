package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "related_project")
public class RelatedProject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="type")
    private String type;
    @Column(nullable = false, name="fullname")
    private String fullName;
    @Column(nullable = false, name="abbreviation")
    private String abbreviation;

    @OneToMany(mappedBy = "relatedProject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    public RelatedProject() { }

    public RelatedProject(String type, String fullName, String abbreviation) {
        this.type = type;
        this.fullName = fullName;
        this.abbreviation = abbreviation;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(List<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}