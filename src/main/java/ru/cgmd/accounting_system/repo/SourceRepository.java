package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.Source;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    Source findByName(String name);
    List<Source> findByInformationResourcesNotEmpty();
}