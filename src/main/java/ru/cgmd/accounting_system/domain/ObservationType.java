package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "observation_type")
public class ObservationType { //вид наблюдения
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_observation_type")
    private Long idObservationType;

    @Column(nullable = false, name="name_observation_type", unique = true)
    private String nameObservationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "id_observation_discipline")
    private ObservationDiscipline observationDiscipline;

    @OneToMany(mappedBy = "observationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public ObservationType() { }

    public ObservationType(String nameObservationType, ObservationDiscipline observationDiscipline) {
        this.nameObservationType = nameObservationType;
        this.observationDiscipline = observationDiscipline;
    }

    public Long getIdObservationType() {
        return idObservationType;
    }
    public void setIdObservationType(Long idObservationType) {
        this.idObservationType = idObservationType;
    }

    public String getNameObservationType() {
        return nameObservationType;
    }
    public void setNameObservationType(String nameObservationType) {
        this.nameObservationType = nameObservationType;
    }

    public ObservationDiscipline getObservationDiscipline() {
        return observationDiscipline;
    }
    public void setObservationDiscipline(ObservationDiscipline observationDiscipline) {
        this.observationDiscipline = observationDiscipline;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}