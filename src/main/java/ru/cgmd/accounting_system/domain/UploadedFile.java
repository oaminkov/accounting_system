package ru.cgmd.accounting_system.domain;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_file")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uploaded_file_generator")
    @SequenceGenerator(name="uploaded_file_generator", sequenceName = "uploaded_file_seq", allocationSize=50)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="path", unique = true)
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "information_resource")
    private InformationResource informationResource;

    public UploadedFile() { }

    public UploadedFile(String name, String path, InformationResource informationResource) {
        this.name = name;
        this.path = path;
        this.informationResource = informationResource;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public InformationResource getInformationResource() {
        return informationResource;
    }
    public void setInformationResource(InformationResource informationResource) { this.informationResource = informationResource; }
}