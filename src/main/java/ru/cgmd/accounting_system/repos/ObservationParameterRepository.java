package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationParameter;

public interface ObservationParameterRepository extends JpaRepository<ObservationParameter, Long> {

}