package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_language")
    private Long idLanguage;

    @Column(nullable = false, name="name_language", unique = true)
    private String nameLanguage;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public Language() { }

    public Language(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public Long getIdLanguage() {
        return idLanguage;
    }
    public void setIdLanguage(Long idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }
    public void setNameLanguage(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}