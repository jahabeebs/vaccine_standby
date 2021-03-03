package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/addPatient")
    public String addPatient(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam boolean healthcareWorkerStatus, @RequestParam String occupation, @RequestParam String phoneNumber,
                             @RequestParam boolean minorityStatus, @RequestParam double distanceFromPharmacy,
                             @RequestParam String age, @RequestParam double priorityScore) {
        Patient patient = new Patient(firstName, healthcareWorkerStatus, lastName, occupation, phoneNumber, minorityStatus, distanceFromPharmacy, age, priorityScore);
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

    @GetMapping("/createForm")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "registerPatient";
    }

    @PostMapping("/saveForm")
    public String savePatientForm(@ModelAttribute Patient patient) {
        return "result";
    }

    @GetMapping("/find/{lastName}")
    public Patient findPatientById(@PathVariable String lastName) {
        return patientRepository.findPatientByLastName(lastName);
    }

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

