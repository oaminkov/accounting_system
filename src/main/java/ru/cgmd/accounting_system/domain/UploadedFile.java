package ru.cgmd.accounting_system.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "uploaded_file")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uploaded_file_generator")
    @SequenceGenerator(name = "uploaded_file_generator", sequenceName = "uploaded_file_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false, unique = true)
    @NonNull
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_information_resource")
    @NonNull
    private InformationResource informationResource;
}