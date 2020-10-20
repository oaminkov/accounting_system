package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observation_scope")  //сфера наблюдений
public class ObservationScope {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_scope_generator")
    @SequenceGenerator(name="observation_scope_generator", sequenceName = "observation_scope_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "infres_observscope",
            joinColumns = { @JoinColumn(name = "id_observation_scope") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_resource") }
    )
    private Set<InformationResource> informationResources = new HashSet<>();

    public ObservationScope() { }

    public ObservationScope(String name) {
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

    public Set<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(Set<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}