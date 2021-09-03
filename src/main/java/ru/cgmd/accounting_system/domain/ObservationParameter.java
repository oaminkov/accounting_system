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
@Table(name = "observation_parameter")
public class ObservationParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_parameter_generator")
    @SequenceGenerator(name = "observation_parameter_generator", sequenceName = "observation_parameter_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_observation_type")
    @ToString.Exclude
    @NonNull
    private ObservationType observationType;

    @ManyToMany
    @JoinTable(
            name = "infres_observparam",
            joinColumns = {@JoinColumn(name = "id_observation_parameter")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}