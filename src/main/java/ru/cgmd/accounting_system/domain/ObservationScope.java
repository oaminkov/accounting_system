package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "observation_scope")  //сфера наблюдений
public class ObservationScope {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_observation_scope")
    private Long idObservationScope;

    @Column(nullable = false, name="name_observation_scope", unique = true)
    private String nameObservationScope;

    @OneToMany(mappedBy = "observationScope", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public ObservationScope() { }

    public ObservationScope(String nameObservationScope) {
        this.nameObservationScope = nameObservationScope;
    }

    public Long getIdObservationScope() {
        return idObservationScope;
    }
    public void setIdObservationScope(Long idObservationScope) {
        this.idObservationScope = idObservationScope;
    }

    public String getNameObservationScope() {
        return nameObservationScope;
    }
    public void setNameObservationScope(String nameObservationScope) {
        this.nameObservationScope = nameObservationScope;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}