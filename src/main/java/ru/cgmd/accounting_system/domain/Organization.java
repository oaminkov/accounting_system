package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_generator")
    @SequenceGenerator(name="organization_generator", sequenceName = "organization_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="name_rus")
    private String nameRus;

    @Column(nullable = false, unique = true)
    private String abbreviation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "id_country")
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "infres_organization",
            joinColumns = { @JoinColumn(name = "id_organization") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_resource") }
    )
    private Set<InformationResource> informationResources = new HashSet<>();

    public Organization() { }

    public Organization(String name, String nameRus, String abbreviation, Country country) {
        this.name = name;
        this.nameRus = nameRus;
        this.abbreviation = abbreviation;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(Set<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}