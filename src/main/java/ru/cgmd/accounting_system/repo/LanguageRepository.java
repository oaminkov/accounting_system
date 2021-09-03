package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.Language;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByName(String name);

    List<Language> findByInformationResourcesNotEmpty();
}