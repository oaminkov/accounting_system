package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationParameter;
import ru.cgmd.accounting_system.domain.ObservationType;
import ru.cgmd.accounting_system.repo.ObservationParameterRepository;

import java.util.List;

@Service
@Transactional
public class ObservationParameterService {
    private final ObservationParameterRepository observationParameterRepository;

    public ObservationParameterService(ObservationParameterRepository observationParameterRepository) {
        this.observationParameterRepository = observationParameterRepository;
    }

    public List<ObservationParameter> listAll() {
        return observationParameterRepository.findAll();
    }

    public void save(ObservationParameter observationParameter) {
        observationParameterRepository.save(observationParameter);
    }

    public ObservationParameter get(long id) {
        return observationParameterRepository.findById(id).get();
    }

    public void delete(long id) {
        observationParameterRepository.deleteById(id);
    }

    public List<ObservationParameter> loadByObservationType(ObservationType observationType) {
        return observationParameterRepository.findByObservationType(observationType);
    }
}