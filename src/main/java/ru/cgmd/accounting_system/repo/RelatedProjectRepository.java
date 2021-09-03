package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.ProjectType;
import ru.cgmd.accounting_system.domain.RelatedProject;

import java.util.List;

public interface RelatedProjectRepository extends JpaRepository<RelatedProject, Long> {
    RelatedProject findByNameAndProjectType(String name, ProjectType projectType);

    List<RelatedProject> findByInformationResourcesNotEmpty();
}