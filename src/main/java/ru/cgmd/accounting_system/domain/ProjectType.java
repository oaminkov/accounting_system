package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_type")
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_type_generator")
    @SequenceGenerator(name="project_type_generator", sequenceName = "project_type_seq", allocationSize=10)
    private Long id;

    @Column(nullable = false, name="name", unique = true)
    private String name;

    @OneToMany(mappedBy = "projectType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelatedProject> relatedProjects;

    public ProjectType() {
    }

    public ProjectType(String name) {
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

    public List<RelatedProject> getRelatedProjects() {
        return relatedProjects;
    }
    public void setRelatedProjects(List<RelatedProject> relatedProjects) {
        this.relatedProjects = relatedProjects;
    }
}