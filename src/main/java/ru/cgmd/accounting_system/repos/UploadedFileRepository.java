package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.InformationProduct;
import ru.cgmd.accounting_system.domain.UploadedFile;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    Set<UploadedFile> findByInformationProduct(InformationProduct informationProduct);
    void deleteByInformationProduct(InformationProduct informationProduct);
}