package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observation_territory")
public class ObservationTerritory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_territory_generator")
    @SequenceGenerator(name="observation_territory_generator", sequenceName = "observation_territory_seq", allocationSize=50)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "infres_observterritory",
            joinColumns = { @JoinColumn(name = "id_observation_territory") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_resource") }
    )
    private Set<InformationResource> informationResources = new HashSet<>();

    public ObservationTerritory() { }

    public ObservationTerritory(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(Set<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}