package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByName(String name);
    List<Language> findByInformationProductsNotEmpty();
}