package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observation_parameter")
public class ObservationParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_observation_type")
    private ObservationType observationType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "infres_observparam",
            joinColumns = { @JoinColumn(name = "id_observation_parameter") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_resource") }
    )
    private Set<InformationResource> informationResources = new HashSet<>();

    public ObservationParameter() {
    }

    public ObservationParameter(String name, ObservationType observationType) {
        this.name = name;
        this.observationType = observationType;
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

    public ObservationType getObservationType() {
        return observationType;
    }
    public void setObservationType(ObservationType observationType) {
        this.observationType = observationType;
    }

    public Set<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(Set<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}