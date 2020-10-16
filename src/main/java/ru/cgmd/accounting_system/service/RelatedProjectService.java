package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.RelatedProject;
import ru.cgmd.accounting_system.repo.RelatedProjectRepository;

import java.util.List;

@Service
@Transactional
public class RelatedProjectService {
    @Autowired
    private RelatedProjectRepository relatedProjectRepository;

    public boolean isExists(String name, String type) {
        if (relatedProjectRepository.findByNameAndType(name, type) == null) {
            return false;
        }
        return true;
    }

    public List<RelatedProject> listAll() {
        return relatedProjectRepository.findAll();
    }

    public List<RelatedProject> findByInformationResourcesExists() {
        return relatedProjectRepository.findByInformationResourcesNotEmpty();
    }

    public void save(RelatedProject relatedProject) {
        relatedProjectRepository.save(relatedProject);
    }

    public RelatedProject get(long id) {
        return relatedProjectRepository.findById(id).get();
    }

    public void delete(long id) {
        relatedProjectRepository.deleteById(id);
    }
}