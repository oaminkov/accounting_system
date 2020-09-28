package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.GeographicalObject;
import ru.cgmd.accounting_system.repos.GeographicalObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GeographicalObjectService {
    @Autowired
    private GeographicalObjectRepository geographicalObjectRepository;

    public boolean isExists(String name) {
        if (geographicalObjectRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<GeographicalObject> listAll() {
        return geographicalObjectRepository.findAll();
    }

    /*public List<GeographicalObject> findByInformationProductsExists() {
        return geographicalObjectRepository.findByInformationProductsNotEmpty();
    }*/

    public void save(GeographicalObject geographicalObject) {
        geographicalObjectRepository.save(geographicalObject);
    }

    public GeographicalObject get(long id) {
        return geographicalObjectRepository.findById(id).get();
    }

    public void delete(long id) {
        geographicalObjectRepository.deleteById(id);
    }
}