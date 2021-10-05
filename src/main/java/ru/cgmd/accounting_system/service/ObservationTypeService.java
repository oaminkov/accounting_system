package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;
import ru.cgmd.accounting_system.domain.ObservationType;
import ru.cgmd.accounting_system.repo.ObservationDisciplineRepository;
import ru.cgmd.accounting_system.repo.ObservationTypeRepository;

import java.util.List;

@Service
@Transactional
public class ObservationTypeService {
    private final ObservationTypeRepository observationTypeRepository;
    private final ObservationDisciplineRepository observationDisciplineRepository;

    public ObservationTypeService(ObservationTypeRepository observationTypeRepository, ObservationDisciplineRepository observationDisciplineRepository) {
        this.observationTypeRepository = observationTypeRepository;
        this.observationDisciplineRepository = observationDisciplineRepository;
    }

    public List<ObservationType> listAll() {
        return observationTypeRepository.findAll();
    }

    public void save(ObservationType observationType) {
        observationTypeRepository.save(observationType);
    }

    public ObservationType get(long id) {
        return observationTypeRepository.findById(id).get();
    }

    public void delete(long id) {
        observationTypeRepository.deleteById(id);
    }

    public List<ObservationType> loadByObservationDiscipline(ObservationDiscipline observationDiscipline) {
        return observationTypeRepository.findByObservationDiscipline(observationDiscipline);
    }
}