package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resource_type")
public class ResourceType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_type_generator")
    @SequenceGenerator(name="resource_type_generator", sequenceName = "resource_type_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "resourceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationResource> informationResources;

    public ResourceType() {
    }

    public ResourceType(String name) {
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

    @Override
    public String toString() {
        return name;
    }
}