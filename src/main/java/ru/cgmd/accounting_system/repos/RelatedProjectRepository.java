package ru.cgmd.accounting_system.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.RelatedProject;

import java.util.List;

public interface RelatedProjectRepository extends JpaRepository<RelatedProject, Long> {
    List<RelatedProject> findByInformationResourcesNotEmpty();
}