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
@Table(name = "observation_method")
public class ObservationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_method_generator")
    @SequenceGenerator(name = "observation_method_generator", sequenceName = "observation_method_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "observationMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<InformationResource> informationResources;
}