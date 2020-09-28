package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.ObservationScope;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ObservationScopeRepository extends JpaRepository<ObservationScope, Long> {
    ObservationScope findByName(String name);
    //List<ObservationScope> findByInformationProductsNotEmpty();
}