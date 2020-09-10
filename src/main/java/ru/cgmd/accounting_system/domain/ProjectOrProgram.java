package ru.cgmd.accounting_system.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_or_program")
public class ProjectOrProgram { //проект/программа
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_name_project_or_program")
    private Long idProjectOrProgram;

    @Column(nullable = false, name="choice_project_or_program")
    private String choiceProjectOrProgram;
    @Column(nullable = false, name="fullname_project_or_program")
    private String fullnameProjectOrProgram;
    @Column(nullable = false, name="abbreviation_project_or_program")
    private String abbreviationProjectOrProgram;

    @OneToMany(mappedBy = "projectOrProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationProduct> informationProducts;

    public ProjectOrProgram() { }

    public ProjectOrProgram(String choiceProjectOrProgram, String fullnameProjectOrProgram, String abbreviationProjectOrProgram) {
        this.choiceProjectOrProgram = choiceProjectOrProgram;
        this.fullnameProjectOrProgram = fullnameProjectOrProgram;
        this.abbreviationProjectOrProgram = abbreviationProjectOrProgram;
    }

    public Long getIdProjectOrProgram() {
        return idProjectOrProgram;
    }
    public void setIdProjectOrProgram(Long idProjectOrProgram) {
        this.idProjectOrProgram = idProjectOrProgram;
    }

    public String getChoiceProjectOrProgram() {
        return choiceProjectOrProgram;
    }
    public void setChoiceProjectOrProgram(String choiceProjectOrProgram) {
        this.choiceProjectOrProgram = choiceProjectOrProgram;
    }

    public String getFullnameProjectOrProgram() {
        return fullnameProjectOrProgram;
    }
    public void setFullnameProjectOrProgram(String fullnameProjectOrProgram) {
        this.fullnameProjectOrProgram = fullnameProjectOrProgram;
    }

    public String getAbbreviationProjectOrProgram() {
        return abbreviationProjectOrProgram;
    }
    public void setAbbreviationProjectOrProgram(String abbreviationProjectOrProgram) {
        this.abbreviationProjectOrProgram = abbreviationProjectOrProgram;
    }

    public List<InformationProduct> getInformationProducts() {
        return informationProducts;
    }
    public void setInformationProducts(List<InformationProduct> informationProducts) {
        this.informationProducts = informationProducts;
    }
}