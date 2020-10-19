package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationTerritory;

public interface ObservationTerritoryRepository extends JpaRepository<ObservationTerritory, Long> {
    ObservationTerritory findByName(String name);
}