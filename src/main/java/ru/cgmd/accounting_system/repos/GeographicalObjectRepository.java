package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.GeographicalObject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GeographicalObjectRepository extends JpaRepository<GeographicalObject, Long> {
    GeographicalObject findByNameGeographicalObject(String name);
    List<GeographicalObject> findByInformationProductsNotEmpty();
}