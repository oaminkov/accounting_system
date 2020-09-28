package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.*;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InformationProductRepository extends JpaRepository<InformationProduct, Long> {
    List<InformationProduct> findByOrderByDateObservationStartAsc();
    /*List<InformationProduct> findByOrderByDateObservationEndDesc();
    List<InformationProduct> findByObservationType(ObservationType observationType);
    List<InformationProduct> findByCountryAndObservationType(Country country, ObservationType observationType);
    List<InformationProduct> findByObservationDiscipline(ObservationDiscipline observationDiscipline);
    List<InformationProduct> findByObservationDisciplineAndObservationType(ObservationDiscipline observationDiscipline, ObservationType observationType);
    List<InformationProduct> findByObservationDisciplineAndObservationTypeAndCountry(ObservationDiscipline observationDiscipline, ObservationType observationType, Country country);
    List<InformationProduct> findByObservationDisciplineAndObservationTypeAndCountryAndOrganization(ObservationDiscipline observationDiscipline,
                                                                                                    ObservationType observationType,
                                                                                                    Country country,
                                                                                                    Organization organization);*/
}