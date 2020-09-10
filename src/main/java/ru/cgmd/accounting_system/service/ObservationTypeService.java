package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.ObservationType;
import ru.cgmd.accounting_system.repos.ObservationDisciplineRepository;
import ru.cgmd.accounting_system.repos.ObservationTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ObservationTypeService {
    @Autowired
    private ObservationTypeRepository observationTypeRepository;
    @Autowired
    private ObservationDisciplineRepository observationDisciplineRepository;

    public List<ObservationType> listAll() {
        return observationTypeRepository.findAll();
    }

    public List<ObservationType> findByInformationProductsExists() {
        return observationTypeRepository.findByInformationProductsNotEmpty();
    }

    public void save(ObservationType observationType) {
        observationTypeRepository.save(observationType);
    }

    public ObservationType get(long idObservationType) {
        return observationTypeRepository.findById(idObservationType).get();
    }

    public void delete(long idObservationType) {
        observationTypeRepository.deleteById(idObservationType);
    }

}