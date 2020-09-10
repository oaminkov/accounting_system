package ru.cgmd.accounting_system.service;

import ru.cgmd.accounting_system.domain.InformationProduct;
import ru.cgmd.accounting_system.repos.InformationProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InformationProductService {
    @Autowired
    private InformationProductRepository informationProductRepository;

    public List<InformationProduct> listAll() {
        return informationProductRepository.findAll();
    }


    public void save(InformationProduct informationProduct) {
        informationProductRepository.save(informationProduct);
    }

    public InformationProduct findByIdInformationProduct(long idInformationProduct) {
        return informationProductRepository.findById(idInformationProduct).get();
    }

    public void delete(long idInformationProduct) {
        informationProductRepository.deleteById(idInformationProduct);
    }
}