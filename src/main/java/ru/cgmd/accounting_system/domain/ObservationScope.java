package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observation_scope")  //сфера наблюдений
public class ObservationScope {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "infprod_observscope",
            joinColumns = { @JoinColumn(name = "id_observation_scope") },
            inverseJoinColumns = { @JoinColumn(name = "id_information_product") }
    )
    private Set<InformationProduct> informationProducts = new HashSet<>();

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

    public Set<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(Set<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}