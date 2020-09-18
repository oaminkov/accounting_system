package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "observation_type")
public class ObservationType { //вид наблюдения
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "observationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObservationParameter> observationParameters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "id_observation_discipline")
    private ObservationDiscipline observationDiscipline;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "infprod_observtype",
            joinColumns = { @JoinColumn(name = "id_observation_type") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_product") }
    )
    private Set<InformationProduct> informationProducts = new HashSet<>();

    public ObservationType() { }

    public ObservationType(String name, ObservationDiscipline observationDiscipline) {
        this.name = name;
        this.observationDiscipline = observationDiscipline;
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

    public ObservationDiscipline getObservationDiscipline() {
        return observationDiscipline;
    }
    public void setObservationDiscipline(ObservationDiscipline observationDiscipline) {
        this.observationDiscipline = observationDiscipline;
    }

    public Set<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(Set<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }

    public List<ObservationParameter> getObservationParameters() {
        return observationParameters;
    }
    public void setObservationParameters(List<ObservationParameter> observationParameters) {
        this.observationParameters = observationParameters;
    }
}