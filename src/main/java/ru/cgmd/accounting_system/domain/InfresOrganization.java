package ru.cgmd.accounting_system.domain;

import javax.persistence.*;

@Entity
@Table(name = "infprod_organization")
public class InfresOrganization {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="main")
    private boolean main;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_information_product", nullable = false)
    private InformationResource informationResource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_organization", nullable = false)
    private Organization organization;

    public InfresOrganization() {
    }

    public InfresOrganization(boolean main, InformationResource informationResource, Organization organization) {
        this.main = main;
        this.informationResource = informationResource;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMain() {
        return main;
    }
    public void setMain(boolean main) {
        this.main = main;
    }

    public InformationResource getInformationResource() {
        return informationResource;
    }
    public void setInformationResource(InformationResource informationResource) {
        this.informationResource = informationResource;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}