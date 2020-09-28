package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.ProjectType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
    List<ProjectType> findByInformationProductsNotEmpty();
}