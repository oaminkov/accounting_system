package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.ObservationDiscipline;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ObservationDisciplineRepository  extends JpaRepository<ObservationDiscipline, Long> {
    ObservationDiscipline findByNameObservationDiscipline(String name);
    List<ObservationDiscipline> findByInformationProductsNotEmpty();
}