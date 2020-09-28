package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
    List<Country> findByInformationProductsNotEmpty();
}