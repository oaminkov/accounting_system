package ru.cgmd.accounting_system.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "project_type")
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_type_generator")
    @SequenceGenerator(name = "project_type_generator", sequenceName = "project_type_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "projectType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<RelatedProject> relatedProjects;
}