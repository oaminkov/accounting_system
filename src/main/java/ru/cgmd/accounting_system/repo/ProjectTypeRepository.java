package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ProjectType;

import java.util.List;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
    ProjectType findByName(String name);
    List<ProjectType> findByRelatedProjectsNotEmpty();
}