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
@Table(name = "observation_scope")  //сфера наблюдений
public class ObservationScope {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_scope_generator")
    @SequenceGenerator(name = "observation_scope_generator", sequenceName = "observation_scope_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "infres_observscope",
            joinColumns = {@JoinColumn(name = "id_observation_scope")},
            inverseJoinColumns = {@JoinColumn(name = "id_information_resource")}
    )
    @ToString.Exclude
    private Set<InformationResource> informationResources = new HashSet<>();
}