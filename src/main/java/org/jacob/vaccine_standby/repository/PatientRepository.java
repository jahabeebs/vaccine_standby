package org.jacob.vaccine_standby.repository;

import org.jacob.vaccine_standby.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findPatientById(Integer id);
    Patient findTopByOrderByPriorityScoreDescCallAttemptsGreaterThanZero();
    Patient findPatientByLastName(String lastName);
    Patient findPatientByLastNameAndFirstName(String lastName, String firstName);
    Patient deletePatientById(Integer id);
    Patient findTopByOrderByPriorityScoreDesc();
    Patient save(Patient patient);
}
