package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.Country;
import ru.cgmd.accounting_system.domain.Organization;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByFullnameOrganizationAndCountry(String name, Country country);
    List<Organization> findByInformationProductsNotEmpty();
}