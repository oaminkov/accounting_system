package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.InformationResource;
import ru.cgmd.accounting_system.repo.InformationResourceRepository;

import java.util.List;

@Service
@Transactional
public class InformationResourceService {
    private final InformationResourceRepository informationResourceRepository;

    public InformationResourceService(InformationResourceRepository informationResourceRepository) {
        this.informationResourceRepository = informationResourceRepository;
    }

    public List<InformationResource> listAll() {
        return informationResourceRepository.findAll();
    }

    public void save(InformationResource informationResource) {
        informationResourceRepository.save(informationResource);
    }

    public void delete(InformationResource informationResource) {
        informationResourceRepository.delete(informationResource);
    }
}