package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Organization> organizations;

    public Country() { }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public List<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(List<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }

    public List<Organization> getOrganizations() { return organizations; }
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return name;
    }
}