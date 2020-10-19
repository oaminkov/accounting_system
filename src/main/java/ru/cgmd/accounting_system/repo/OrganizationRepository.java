package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}