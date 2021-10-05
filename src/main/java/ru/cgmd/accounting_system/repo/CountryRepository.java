package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);

    List<Country> findByInformationResourcesNotEmpty();

    List<Country> findByOrganizationsNotEmpty();
}