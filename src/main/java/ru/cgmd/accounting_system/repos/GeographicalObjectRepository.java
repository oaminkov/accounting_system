package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.GeographicalObject;

public interface GeographicalObjectRepository extends JpaRepository<GeographicalObject, Long> {
    GeographicalObject findByName(String name);
}