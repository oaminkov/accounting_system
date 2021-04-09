package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_generator")
    @SequenceGenerator(name="source_generator", sequenceName = "source_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    public Source() {
    }

    public Source(String name) {
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