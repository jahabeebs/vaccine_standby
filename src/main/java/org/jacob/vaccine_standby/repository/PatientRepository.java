package org.jacob.vaccine_standby.repository;

import org.jacob.vaccine_standby.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findPatientByLastName(String lastName);
    Patient findPatientByLastNameAndFirstName(String lastName, String firstName);
    Patient deletePatientById(Integer id);
//    Patient findByIdNotNullOrderByPriorityScoreAsc(Integer numberToList);
    Patient findTopByOrderByPriorityScoreDesc();
}