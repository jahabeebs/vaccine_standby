package org.jacob.vaccine_standby.repository;

import org.jacob.vaccine_standby.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findPatientById(Integer id);
    Patient deletePatientById(Integer id);
//    List<Patient> findByIdNotNullOrderByPriorityScoreAsc(Integer numberToList);
    Patient findTopByOrderByPriorityScoreDesc();
}
