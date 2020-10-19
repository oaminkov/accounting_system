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
    @SequenceGenerator(name="organization_generator", sequenceName = "organization_seq", allocationSize=50)
    private Long id;

    @Column(nullable = false, unique = true)
    private String abbreviation;

    @Column(name="name_rus")
    private String nameRus;

    @Column(name="name_eng")
    private String nameEng;

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

    public Organization(String abbreviation, String nameRus, String nameEng, Country country) {
        this.abbreviation = abbreviation;
        this.nameRus = nameRus;
        this.nameEng = nameEng;
        this.country = country;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getNameRus() {
        return nameRus;
    }
    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getNameEng() {
        return nameEng;
    }
    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
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