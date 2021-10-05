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
@Table(name = "related_project")
public class RelatedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "related_project_generator")
    @SequenceGenerator(name = "related_project_generator", sequenceName = "related_project_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(name = "name_rus")
    @NonNull
    private String nameRus;

    @Column(nullable = false)
    @NonNull
    private String abbreviation;

    @Column(name = "abbreviation_rus")
    @NonNull
    private String abbreviationRus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_project_type")
    @ToString.Exclude
    @NonNull
    private ProjectType projectType;

    @OneToMany(mappedBy = "relatedProject", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<InformationResource> informationResources;
}