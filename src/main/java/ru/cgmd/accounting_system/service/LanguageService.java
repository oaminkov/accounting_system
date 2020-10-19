package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.Language;
import ru.cgmd.accounting_system.repo.LanguageRepository;

import java.util.List;

@Service
@Transactional
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public boolean isExists(String name) {
        if (languageRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<Language> listAll() {
        return languageRepository.findAll();
    }

    public List<Language> findByInformationResourcesExists() {
        return languageRepository.findByInformationResourcesNotEmpty();
    }

    public void save(Language language) {
        languageRepository.save(language);
    }

    public Language get(long id) {
        return languageRepository.findById(id).get();
    }

    public void delete(long id) {
        languageRepository.deleteById(id);
    }
}