package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ResourceType;
import ru.cgmd.accounting_system.repo.ResourceTypeRepository;

import java.util.List;

@Service
@Transactional
public class ResourceTypeService {
    private final ResourceTypeRepository resourceTypeRepository;

    public ResourceTypeService(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }

    public boolean isExists(String name) {
        if (resourceTypeRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ResourceType> listAll() {
        return resourceTypeRepository.findAll();
    }

    public List<ResourceType> findByInformationResourcesExists() {
        return resourceTypeRepository.findByInformationResourcesNotEmpty();
    }

    public void save(ResourceType resourceType) {
        resourceTypeRepository.save(resourceType);
    }

    public ResourceType get(long id) {
        return resourceTypeRepository.findById(id).get();
    }

    public void delete(long id) {
        resourceTypeRepository.deleteById(id);
    }
}