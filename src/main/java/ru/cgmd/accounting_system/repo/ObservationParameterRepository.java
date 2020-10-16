package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationParameter;
import ru.cgmd.accounting_system.domain.ObservationType;

import java.util.List;

public interface ObservationParameterRepository extends JpaRepository<ObservationParameter, Long> {

    List<ObservationParameter> findByObservationType(ObservationType observationType);
}