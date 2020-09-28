package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.Country;
import ru.cgmd.accounting_system.repos.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public boolean isExists(String name) {
        if (countryRepository.findByName(name) == null) {
            return false;
        }
        return true;
    }

    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    public List<Country> findByInformationProductsExists() {
        return countryRepository.findByInformationProductsNotEmpty();
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