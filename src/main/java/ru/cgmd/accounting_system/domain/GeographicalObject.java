package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "geographical_object")
public class GeographicalObject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_geographical_object")
    private Long idGeographicalObject;

    @Column(nullable = false, name="name_geographical_object", unique = true)
    private String nameGeographicalObject;

    @OneToMany(mappedBy = "geographicalObject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public GeographicalObject() { }

    public GeographicalObject(String nameGeographicalObject) {
        this.nameGeographicalObject = nameGeographicalObject;
    }

    public Long getIdGeographicalObject() { return idGeographicalObject; }
    public void setIdGeographicalObject(Long idGeographicalObject) { this.idGeographicalObject = idGeographicalObject; }

    public String getNameGeographicalObject() { return nameGeographicalObject; }
    public void setNameGeographicalObject(String nameGeographicalObject) { this.nameGeographicalObject = nameGeographicalObject; }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}