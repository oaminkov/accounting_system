package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organization")
public class Organization { //организация
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_organization")
    private Long idOrganization;

    @Column(nullable = false, name="fullname_organization", unique = true)
    private String fullnameOrganization;

    @Column(nullable = false, name="abbreviation_organization")
    private String abbreviationOrganization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "id_country")
    private Country country;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public Organization() { }

    public Organization(Country country, String fullnameOrganization, String abbreviationOrganization) {
        this.country = country;
        this.fullnameOrganization = fullnameOrganization;
        this.abbreviationOrganization = abbreviationOrganization;
    }

    public Long getIdOrganization() {
        return idOrganization;
    }
    public void setIdOrganization(Long idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public String getFullnameOrganization() {
        return fullnameOrganization;
    }
    public void setFullnameOrganization(String fullnameOrganization) {
        this.fullnameOrganization = fullnameOrganization;
    }

    public String getAbbreviationOrganization() {
        return abbreviationOrganization;
    }
    public void setAbbreviationOrganization(String abbreviationOrganization) {
        this.abbreviationOrganization = abbreviationOrganization;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}