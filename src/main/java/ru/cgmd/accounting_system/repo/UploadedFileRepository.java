package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.InformationResource;
import ru.cgmd.accounting_system.domain.UploadedFile;

import java.util.Set;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    Set<UploadedFile> findByInformationResource(InformationResource informationResource);

    void deleteByInformationResource(InformationResource informationResource);
}