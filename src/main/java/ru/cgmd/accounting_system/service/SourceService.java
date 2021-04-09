package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.Source;
import ru.cgmd.accounting_system.repo.SourceRepository;

import java.util.List;

@Service
@Transactional
public class SourceService {
    private final SourceRepository sourceRepository;

    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public boolean isExists(String name) {
        if (sourceRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<Source> listAll() {
        return sourceRepository.findAll();
    }

    public List<Source> findByInformationResourcesExists() {
        return sourceRepository.findByInformationResourcesNotEmpty();
    }

    public void save(Source source) {
        sourceRepository.save(source);
    }

    public Source get(long id) {
        return sourceRepository.findById(id).get();
    }

    public void delete(long id) {
        sourceRepository.deleteById(id);
    }
}