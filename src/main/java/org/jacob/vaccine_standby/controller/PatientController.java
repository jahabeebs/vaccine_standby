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
                             @RequestParam boolean minorityStatus, @RequestParam double milesFromPharmacy,
                             @RequestParam String age) {
        Patient patient = new Patient(firstName, healthcareWorkerStatus, lastName, occupation, phoneNumber, minorityStatus, milesFromPharmacy, age);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setHealthcareWorkerStatus(healthcareWorkerStatus);
        patient.setPhoneNumber(phoneNumber);
        patient.setMinorityStatus(minorityStatus);
        patient.setMilesFromPharmacy(milesFromPharmacy);
        patient.setAge(age);
        patient.assignPatientPriorityScore();
        patientRepository.save(patient);
        return "Patient Registered Successfully";
    }

    @GetMapping("/createForm")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "index";
    }

    @PostMapping("/saveForm")
    public String savePatientForm(@ModelAttribute Patient patient) {
        return "index";
    }

    @GetMapping("/{id}")
    public Patient findPatientById(@PathVariable Integer id) {
        return patientRepository.findPatientById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Patient deletePatientById(@PathVariable Integer id) {
        return patientRepository.deletePatientById(id);
    }

    @GetMapping("/find/{lastName}{firstName}")
    public Patient findPatientByLastNameAndFirstName(@PathVariable String lastName, @PathVariable String firstName) {
        return patientRepository.findPatientByLastNameAndFirstName(lastName, firstName);
    }

    @GetMapping("/find/{lastName}")
    public Patient findPatientByLastName(@PathVariable String lastName) {
        return patientRepository.findPatientByLastName(lastName);
    }

    @GetMapping("/listTop")
    public Patient findTopByOrderByPriorityScoreDesc() { return patientRepository.findTopByOrderByPriorityScoreDesc();
    }
}

