package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.InformationResource;
import ru.cgmd.accounting_system.repos.InformationResourceRepository;

import java.util.List;

@Service
@Transactional
public class InformationResourceService {
    @Autowired
    private InformationResourceRepository informationResourceRepository;

    public List<InformationResource> listAll() {
        return informationResourceRepository.findAll();
    }


    public void save(InformationResource informationResource) {
        informationResourceRepository.save(informationResource);
    }

    public InformationResource findByIdInformationResource(long id) {
        return informationResourceRepository.findById(id).get();
    }

    public void delete(InformationResource informationResource) {
        informationResourceRepository.delete(informationResource);
    }
}