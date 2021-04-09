package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationScope;
import ru.cgmd.accounting_system.repo.ObservationScopeRepository;

import java.util.List;

@Service
@Transactional
public class ObservationScopeService {
    private final ObservationScopeRepository observationScopeRepository;

    public ObservationScopeService(ObservationScopeRepository observationScopeRepository) {
        this.observationScopeRepository = observationScopeRepository;
    }

    public boolean isExists(String name) {
        if (observationScopeRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationScope> listAll() {
        return observationScopeRepository.findAll();
    }

    /*public List<ObservationScope> findByInformationProductsExists() {
        return observationScopeRepository.findByInformationProductsNotEmpty();
    }*/

    public void save(ObservationScope observationScope) {
        observationScopeRepository.save(observationScope);
    }

    public ObservationScope get(long id) {
        return observationScopeRepository.findById(id).get();
    }

    public void delete(long id) {
        observationScopeRepository.deleteById(id);
    }

}