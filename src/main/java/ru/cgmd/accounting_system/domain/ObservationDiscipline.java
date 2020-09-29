package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "observation_discipline")
public class ObservationDiscipline {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "observationDiscipline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObservationType> observationTypes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "infres_observdiscipl",
            joinColumns = { @JoinColumn(name = "id_observation_discipline") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_resource") }
    )
    private Set<InformationResource> informationResources = new HashSet<>();

    public ObservationDiscipline() { }

    public ObservationDiscipline(String name) {
        this.name = name;
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

    public List<ObservationType> getObservationTypes() {
        return observationTypes;
    }
    public void setObservationTypes(List<ObservationType> observationTypes) {
        this.observationTypes = observationTypes;
    }

    public Set<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(Set<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}