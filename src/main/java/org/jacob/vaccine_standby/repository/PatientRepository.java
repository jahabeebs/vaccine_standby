package org.jacob.vaccine_standby.repository;

import org.jacob.vaccine_standby.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findPatientById(Integer id);

    Patient findTopByCalledFalseOrderByPriorityScoreDesc();

    void deleteById(Integer id);

    Patient findTopByCalledTrueOrderByPriorityScoreDesc();

    Patient save(Patient patient);
}
