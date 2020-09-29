package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.Organization;
import ru.cgmd.accounting_system.repos.OrganizationRepository;

import java.util.List;

@Service
@Transactional
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> listAll() {
        return organizationRepository.findAll();
    }

    /*public List<Organization> findByInformationProductsExists() {
        return organizationRepository.findByInformationProductsNotEmpty();
    }*/

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