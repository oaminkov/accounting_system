package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "observation_discipline")
public class ObservationDiscipline { //дисциплина наблюдения
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_observation_discipline")
    private Long idObservationDiscipline;

    @Column(nullable = false, name="name_observation_discipline", unique = true)
    private String nameObservationDiscipline;

    @OneToMany(mappedBy = "observationDiscipline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public ObservationDiscipline() { }

    public ObservationDiscipline(String nameObservationDiscipline) {
        this.nameObservationDiscipline = nameObservationDiscipline;
    }

    public Long getIdObservationDiscipline() {
        return idObservationDiscipline;
    }
    public void setIdObservationDiscipline(Long idObservationDiscipline) {
        this.idObservationDiscipline = idObservationDiscipline;
    }

    public String getNameObservationDiscipline() {
        return nameObservationDiscipline;
    }
    public void setNameObservationDiscipline(String nameObservationDiscipline) {
        this.nameObservationDiscipline = nameObservationDiscipline;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}