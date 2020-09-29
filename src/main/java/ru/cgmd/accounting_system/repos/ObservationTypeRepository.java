package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;
import ru.cgmd.accounting_system.domain.ObservationType;

import java.util.List;

public interface ObservationTypeRepository extends JpaRepository<ObservationType, Long> {
    List<ObservationType> findByObservationDiscipline(ObservationDiscipline observationDiscipline);
}