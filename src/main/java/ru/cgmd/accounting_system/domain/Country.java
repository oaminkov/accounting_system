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
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(name = "country_generator", sequenceName = "country_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<InformationResource> informationResources;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Organization> organizations;
}