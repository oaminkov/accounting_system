package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.Language;
import ru.cgmd.accounting_system.repos.LanguageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public boolean isExists(String name) {
        if (languageRepository.findByNameLanguage(name) == null) {
            return false;
        }
        return true;
    }

    public List<Language> listAll() {
        return languageRepository.findAll();
    }

    public List<Language> findByInformationProductsExists() {
        return languageRepository.findByInformationProductsNotEmpty();
    }

    public void save(Language language) {
        languageRepository.save(language);
    }

    public Language get(long idLanguage) {
        return languageRepository.findById(idLanguage).get();
    }

    public void delete(long idLanguage) {
        languageRepository.deleteById(idLanguage);
    }
}