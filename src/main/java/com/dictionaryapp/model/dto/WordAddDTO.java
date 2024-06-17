package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class WordAddDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    private String term;

    @NotBlank
    @Size(min = 2, max = 40)
    private String translation;

    @Size(min = 2, max = 200)
    private String example;

    private LocalDate inputDate;

    private LanguageDTO language;

    private UserDTO addedBy;

    public WordAddDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public UserDTO getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDTO addedBy) {
        this.addedBy = addedBy;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageDTO getLanguage() {
        return language;
    }
}
