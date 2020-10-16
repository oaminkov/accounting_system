package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationScope;

public interface ObservationScopeRepository extends JpaRepository<ObservationScope, Long> {
    ObservationScope findByName(String name);
}