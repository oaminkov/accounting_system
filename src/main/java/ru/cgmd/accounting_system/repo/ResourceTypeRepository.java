package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ResourceType;

import java.util.List;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {
    ResourceType findByName(String name);

    List<ResourceType> findByInformationResourcesNotEmpty();
}