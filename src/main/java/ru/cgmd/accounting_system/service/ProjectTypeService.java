package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ProjectType;
import ru.cgmd.accounting_system.repo.ProjectTypeRepository;

import java.util.List;

@Service
@Transactional
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypeRepository;

    public ProjectTypeService(ProjectTypeRepository projectTypeRepository) {
        this.projectTypeRepository = projectTypeRepository;
    }

    public boolean isExists(String name) {
        if (projectTypeRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ProjectType> listAll() {
        return projectTypeRepository.findAll();
    }

    public List<ProjectType> findByRelatedProjectsExists() {
        return projectTypeRepository.findByRelatedProjectsNotEmpty();
    }

    public void save(ProjectType projectType) {
        projectTypeRepository.save(projectType);
    }

    public ProjectType get(long id) {
        return projectTypeRepository.findById(id).get();
    }

    public void delete(long id) {
        projectTypeRepository.deleteById(id);
    }
}
