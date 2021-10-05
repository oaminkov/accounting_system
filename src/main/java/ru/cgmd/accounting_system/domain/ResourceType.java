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
@Table(name = "resource_type")
public class ResourceType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_type_generator")
    @SequenceGenerator(name = "resource_type_generator", sequenceName = "resource_type_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "resourceType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<InformationResource> informationResources;
}