package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;

import java.util.List;

public interface ObservationDisciplineRepository extends JpaRepository<ObservationDiscipline, Long> {
    ObservationDiscipline findByName(String name);
    List<ObservationDiscipline> findAllByOrderByIdAsc();
}