package ru.cgmd.accounting_system.domain;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_file")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="path", unique = true)
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "information_product")
    private InformationProduct informationProduct;

    public UploadedFile() { }

    public UploadedFile(String name, String path, InformationProduct informationProduct) {
        this.name = name;
        this.path = path;
        this.informationProduct = informationProduct;
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

    public InformationProduct getInformationProduct() {
        return informationProduct;
    }
    public void setInformationProduct(InformationProduct informationProduct) { this.informationProduct = informationProduct; }
}