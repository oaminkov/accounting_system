package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.ObservationScope;
import ru.cgmd.accounting_system.repos.ObservationScopeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ObservationScopeService {
    @Autowired
    private ObservationScopeRepository observationScopeRepository;

    public boolean isExists(String name) {
        if (observationScopeRepository.findByNameObservationScope(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationScope> listAll() {
        return observationScopeRepository.findAll();
    }

    public List<ObservationScope> findByInformationProductsExists() {
        return observationScopeRepository.findByInformationProductsNotEmpty();
    }

    public void save(ObservationScope observationScope) {
        observationScopeRepository.save(observationScope);
    }

    public ObservationScope get(long idObservationScope) {
        return observationScopeRepository.findById(idObservationScope).get();
    }

    public void delete(long idObservationScope) {
        observationScopeRepository.deleteById(idObservationScope);
    }

}