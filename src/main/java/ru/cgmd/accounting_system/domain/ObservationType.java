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
@Table(name = "observation_type")
public class ObservationType { //вид наблюдения
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_type_generator")
    @SequenceGenerator(name = "observation_type_generator", sequenceName = "observation_type_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_observation_discipline")
    @ToString.Exclude
    @NonNull
    private ObservationDiscipline observationDiscipline;

    @OneToMany(mappedBy = "observationType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ObservationParameter> observationParameters;

    @ManyToMany
    @JoinTable(
            name = "infres_observtype",
            joinColumns = {@JoinColumn(name = "id_observation_type")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}