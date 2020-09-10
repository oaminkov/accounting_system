package ru.cgmd.accounting_system.domain;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_file")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_uploaded_file")
    private Long idUploadedFile;

    @Column(nullable = false, name="name_uploaded_file")
    private String nameUploadedFile;

    @Column(nullable = false, name="path_uploaded_file", unique = true)
    private String pathUploadedFile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "information_product")
    private InformationProduct informationProduct;

    public UploadedFile() { }

    public UploadedFile(String nameUploadedFile, String pathUploadedFile, InformationProduct informationProduct) {
        this.nameUploadedFile = nameUploadedFile;
        this.pathUploadedFile = pathUploadedFile;
        this.informationProduct = informationProduct;
    }

    public Long getIdUploadedFile() {
        return idUploadedFile;
    }
    public void setIdUploadedFile(Long idUploadedFile) {
        this.idUploadedFile = idUploadedFile;
    }

    public String getNameUploadedFile() {
        return nameUploadedFile;
    }
    public void setNameUploadedFile(String nameUploadedFile) {
        this.nameUploadedFile = nameUploadedFile;
    }

    public String getPathUploadedFile() {
        return pathUploadedFile;
    }
    public void setPathUploadedFile(String pathUploadedFile) {
        this.pathUploadedFile = pathUploadedFile;
    }

    public InformationProduct getInformationProduct() {
        return informationProduct;
    }
    public void setInformationProduct(InformationProduct informationProduct) { this.informationProduct = informationProduct; }
}