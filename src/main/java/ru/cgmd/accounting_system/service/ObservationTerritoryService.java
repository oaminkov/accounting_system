package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.ObservationTerritory;
import ru.cgmd.accounting_system.repo.ObservationTerritoryRepository;

import java.util.List;

@Service
@Transactional
public class ObservationTerritoryService {
    @Autowired
    private ObservationTerritoryRepository observationTerritoryRepository;

    public boolean isExists(String name) {
        if (observationTerritoryRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<ObservationTerritory> listAll() {
        return observationTerritoryRepository.findAll();
    }

    public void save(ObservationTerritory observationTerritory) {
        observationTerritoryRepository.save(observationTerritory);
    }

    public ObservationTerritory get(long id) {
        return observationTerritoryRepository.findById(id).get();
    }

    public void delete(long id) {
        observationTerritoryRepository.deleteById(id);
    }
}