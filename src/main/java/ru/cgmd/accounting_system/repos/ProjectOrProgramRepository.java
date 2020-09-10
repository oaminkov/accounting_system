package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.ProjectOrProgram;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectOrProgramRepository extends JpaRepository<ProjectOrProgram, Long> {
    List<ProjectOrProgram> findByInformationProductsNotEmpty();
}