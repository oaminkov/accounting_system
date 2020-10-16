package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationMethod;
import ru.cgmd.accounting_system.repo.ObservationMethodRepository;

import java.util.List;

@Service
@Transactional
public class ObservationMethodService {
    @Autowired
    private ObservationMethodRepository observationMethodRepository;

    public boolean isExists(String name) {
        if (observationMethodRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationMethod> listAll() {
        return observationMethodRepository.findAll();
    }

    /*public List<ObservationMethod> findByInformationProductsExists() {
        return observationMethodRepository.findByInformationProductsNotEmpty();
    }*/

    public void save(ObservationMethod observationMethod) {
        observationMethodRepository.save(observationMethod);
    }

    public ObservationMethod get(long id) {
        return observationMethodRepository.findById(id).get();
    }

    public void delete(long id) {
        observationMethodRepository.deleteById(id);
    }
}