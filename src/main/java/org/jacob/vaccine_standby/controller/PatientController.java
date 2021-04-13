package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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

    @GetMapping("/")
    public String showPatientForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "index";
    }

    @PostMapping("/register")
    public String submitPatientForm(@ModelAttribute("patient") Patient patient) {
        patient.assignPatientPriorityScore();
        patientRepository.save(patient);
        System.out.println(patient);
        return "registration_successful";
    }

    @PostMapping("/saveForm")
    public String savePatientForm(@ModelAttribute("patient") Patient patient) {
        return "registration_successful";
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
    public Patient findTopByOrderByPriorityScoreDesc() {
        return patientRepository.findTopByOrderByPriorityScoreDesc();
    }
}

