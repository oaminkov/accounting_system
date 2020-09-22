package ru.cgmd.accounting_system.domain;

import javax.persistence.*;

@Entity
@Table(name = "infprod_organization")
public class InfprodOrganization {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="main")
    private boolean main;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_information_product", nullable = false)
    private InformationProduct informationProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_organization", nullable = false)
    private Organization organization;

    public InfprodOrganization() {
    }

    public InfprodOrganization(boolean main, InformationProduct informationProduct, Organization organization) {
        this.main = main;
        this.informationProduct = informationProduct;
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

    public InformationProduct getInformationProduct() {
        return informationProduct;
    }
    public void setInformationProduct(InformationProduct informationProduct) {
        this.informationProduct = informationProduct;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}