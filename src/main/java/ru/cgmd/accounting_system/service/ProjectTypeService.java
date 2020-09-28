package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.ProjectType;
import ru.cgmd.accounting_system.repos.ProjectTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectTypeService {
    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    public List<ProjectType> listAll() {
        return projectTypeRepository.findAll();
    }

    public List<ProjectType> findByInformationProductsExists() {
        return projectTypeRepository.findByInformationProductsNotEmpty();
    }

    public void save(ProjectType projectType) {
        projectTypeRepository.save(projectType);
    }

    public ProjectType get(long idProjectOrProgram) {
        return projectTypeRepository.findById(idProjectOrProgram).get();
    }

    public void delete(long idProjectOrProgram) {
        projectTypeRepository.deleteById(idProjectOrProgram);
    }
}