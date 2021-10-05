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
@Table(name = "observation_territory")
public class ObservationTerritory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_territory_generator")
    @SequenceGenerator(name = "observation_territory_generator", sequenceName = "observation_territory_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "infres_observterritory",
            joinColumns = {@JoinColumn(name = "id_observation_territory")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}