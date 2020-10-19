package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.InformationResource;

import java.util.List;

public interface InformationResourceRepository extends JpaRepository<InformationResource, Long> {
    List<InformationResource> findByOrderByDateObservationStartAsc();
}