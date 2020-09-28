package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.ObservationDiscipline;
import ru.cgmd.accounting_system.repos.ObservationDisciplineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ObservationDisciplineService {
    @Autowired
    private ObservationDisciplineRepository observationDisciplineRepository;

    public boolean isExists(String name) {
        if (observationDisciplineRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationDiscipline> listAll() {
        return observationDisciplineRepository.findAll();
    }

    /*public List<ObservationDiscipline> findByInformationProductsExists() {
        return observationDisciplineRepository.findByInformationProductsNotEmpty();
    }*/

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