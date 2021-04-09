package ru.cgmd.accounting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cgmd.accounting_system.domain.Country;
import ru.cgmd.accounting_system.repo.CountryRepository;

import java.util.List;

@Service
@Transactional
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public boolean isExists(String name) {
        if (countryRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    public List<Country> findByInformationResourcesExists() {
        return countryRepository.findByInformationResourcesNotEmpty();
    }

    public void save(Country country) {
        countryRepository.save(country);
    }

    public Country get(long id) {
        return countryRepository.findById(id).get();
    }

    public void delete(long id) {
        countryRepository.deleteById(id);
    }
}