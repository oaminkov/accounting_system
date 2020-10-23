package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "observation_method")
public class ObservationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_method_generator")
    @SequenceGenerator(name="observation_method_generator", sequenceName = "observation_method_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "observationMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    public ObservationMethod() {
    }

    public ObservationMethod(String name) {
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

    public List<InformationResource> getInformationResources() {
        return informationResources;
    }
    public void setInformationResources(List<InformationResource> informationResources) {
        this.informationResources = informationResources;
    }
}