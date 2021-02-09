package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/add")
    public String addPatient(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam boolean healthcareWorkerStatus, @RequestParam String phoneNumber,
                             @RequestParam boolean minorityStatus, @RequestParam double distanceFromPharmacy,
                             @RequestParam int age, @RequestParam double priorityScore) {
        Patient patient = new Patient(firstName, healthcareWorkerStatus, lastName, phoneNumber, minorityStatus, distanceFromPharmacy, age, priorityScore);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setHealthcareWorkerStatus(healthcareWorkerStatus);
        patient.setPhoneNumber(phoneNumber);
        patient.setMinorityStatus(minorityStatus);
        patient.setMilesFromPharmacy(distanceFromPharmacy);
        patient.setAge(age);
        patient.assignPatientPriorityScore(patient);
        patientRepository.save(patient);
        return "Patient Registered Successfully";
    }

    @GetMapping("/find/{lastName}")
    public Patient findPatientById(@PathVariable String lastName) {
        return patientRepository.findPatientByLastName(lastName);
    }

//    @GetMapping("/list/{numberToList}")
//    public Patient findByIdNotNullOrderByPriorityScoreAsc(@PathVariable Integer numberToList) {
//        return patientRepository.findByIdNotNullOrderByPriorityScoreAsc(numberToList);
//    }

    @GetMapping("/find/{lastName}{firstName}")
    public Patient findPatientByLastNameAndFirstName(@PathVariable String lastName, @PathVariable String firstName) {
        return patientRepository.findPatientByLastNameAndFirstName(lastName, firstName);
    }

    @GetMapping("/list")
    public Patient findTopByOrderByPriorityScoreDesc() { return patientRepository.findTopByOrderByPriorityScoreDesc();
    }

    @DeleteMapping("/delete/{id}")
    public Patient deletePatientById(@PathVariable Integer id) {
        return patientRepository.deletePatientById(id);
    }

}
