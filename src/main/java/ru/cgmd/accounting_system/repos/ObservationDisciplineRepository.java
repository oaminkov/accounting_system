package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;

public interface ObservationDisciplineRepository  extends JpaRepository<ObservationDiscipline, Long> {
    ObservationDiscipline findByName(String name);
}