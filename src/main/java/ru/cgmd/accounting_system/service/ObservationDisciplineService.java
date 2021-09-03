package ru.cgmd.accounting_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationDiscipline;
import ru.cgmd.accounting_system.repo.ObservationDisciplineRepository;

import java.util.List;

@Service
@Transactional
public class ObservationDisciplineService {
    private final ObservationDisciplineRepository observationDisciplineRepository;

    public ObservationDisciplineService(ObservationDisciplineRepository observationDisciplineRepository) {
        this.observationDisciplineRepository = observationDisciplineRepository;
    }

    public boolean isExists(String name) {
        if (observationDisciplineRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationDiscipline> listAll() {
        return observationDisciplineRepository.findAllByOrderByIdAsc();
    }

    public void save(ObservationDiscipline observationDiscipline) {
        observationDisciplineRepository.save(observationDiscipline);
    }

    public ObservationDiscipline get(long id) {
        return observationDisciplineRepository.findById(id).get();
    }

    public void delete(long id) {
        observationDisciplineRepository.deleteById(id);
    }
}