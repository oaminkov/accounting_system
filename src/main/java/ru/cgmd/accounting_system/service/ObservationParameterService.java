package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationParameter;
import ru.cgmd.accounting_system.domain.ObservationType;
import ru.cgmd.accounting_system.repos.ObservationDisciplineRepository;
import ru.cgmd.accounting_system.repos.ObservationParameterRepository;
import ru.cgmd.accounting_system.repos.ObservationTypeRepository;

import java.util.List;

@Service
@Transactional
public class ObservationParameterService {
    @Autowired
    private ObservationParameterRepository observationParameterRepository;

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

}