package ru.cgmd.accounting_system.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_generator")
    @SequenceGenerator(name = "organization_generator", sequenceName = "organization_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @Column(name = "name_rus")
    @NonNull
    private String nameRus;

    @Column(nullable = false, unique = true)
    @NonNull
    private String abbreviation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_country")
    @ToString.Exclude
    @NonNull
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "infres_organization",
            joinColumns = {@JoinColumn(name = "id_organization")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}