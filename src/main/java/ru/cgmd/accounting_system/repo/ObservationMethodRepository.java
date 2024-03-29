package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationMethod;

public interface ObservationMethodRepository extends JpaRepository<ObservationMethod, Long> {
    ObservationMethod findByName(String name);
}