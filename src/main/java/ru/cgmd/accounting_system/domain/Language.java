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
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_generator")
    @SequenceGenerator(name = "language_generator", sequenceName = "language_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<InformationResource> informationResources;
}