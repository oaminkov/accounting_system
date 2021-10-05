package ru.cgmd.accounting_system.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "observation_discipline")
public class ObservationDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_discipline_generator")
    @SequenceGenerator(name = "observation_discipline_generator", sequenceName = "observation_discipline_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "observationDiscipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ObservationType> observationTypes;

    @ManyToMany
    @JoinTable(
            name = "infres_observdiscipl",
            joinColumns = {@JoinColumn(name = "id_observation_discipline")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}