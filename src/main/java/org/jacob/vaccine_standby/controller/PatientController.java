package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/add")
    public String addPatient(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String healthcareWorkerStatus, @RequestParam String phoneNumber,
                             @RequestParam String race, @RequestParam double distanceFromPharmacy,
                             @RequestParam int age, @RequestParam double priorityScore) {
        Patient patient = new Patient(firstName, healthcareWorkerStatus, lastName, phoneNumber, race, distanceFromPharmacy, age, priorityScore);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setHealthcareWorkerStatus(healthcareWorkerStatus);
        patient.setPhoneNumber(phoneNumber);
        patient.setRace(race);
        patient.setDistanceFromPharmacy(distanceFromPharmacy);
        patient.setAge(age);
        patient.setPriorityScore(priorityScore);
        patientRepository.save(patient);
        return "Patient Registered Successfully";
    }

    @GetMapping("/find/{id}")
    public Patient findPatientById(@PathVariable Integer id) {
        return patientRepository.findPatientById(id);
    }

//    @GetMapping("/list/{numberToList}")
//    public List<Patient> findByIdNotNullOrderByPriorityScoreAsc(@PathVariable Integer numberToList) {
//        return patientRepository.findByIdNotNullOrderByPriorityScoreAsc(numberToList);
//    }

    @GetMapping("/list")
    public Patient findTopByOrderByPriorityScoreDesc() {
        return patientRepository.findTopByOrderByPriorityScoreDesc();
    }

    @DeleteMapping("/delete/{id}")
    public Patient deletePatientById(@PathVariable Integer id) {
        return patientRepository.deletePatientById(id);
    }

}

