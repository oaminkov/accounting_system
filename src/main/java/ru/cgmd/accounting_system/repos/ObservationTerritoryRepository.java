package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationTerritory;

public interface ObservationTerritoryRepository extends JpaRepository<ObservationTerritory, Long> {
    ObservationTerritory findByName(String name);
}