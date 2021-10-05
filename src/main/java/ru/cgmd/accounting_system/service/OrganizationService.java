package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.Organization;
import ru.cgmd.accounting_system.repo.OrganizationRepository;

import java.util.List;

@Service
@Transactional
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> listAll() {
        return organizationRepository.findAll();
    }

    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    public Organization get(long id) {
        return organizationRepository.findById(id).get();
    }

    public void delete(long id) {
        organizationRepository.deleteById(id);
    }
}