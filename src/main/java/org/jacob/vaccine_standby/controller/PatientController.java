package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

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

    @GetMapping("/topPerformer")
    public ModelAndView showTop() {
        ModelAndView mav = new ModelAndView("highest_priority_patient");
        Patient patient = patientRepository.findTopByOrderByPriorityScoreDesc();
        mav.addObject("patient", patient);
        return mav;
    }

    @GetMapping("/topMissed")
    public ModelAndView callAttemptEnforcer() {
        ModelAndView mav = new ModelAndView("highest_priority_missed_patient");
        Patient patient = patientRepository.findTopByCalledTrueOrderByPriorityScoreDesc();
        mav.addObject("patient", patient);
        return mav;
    }

    @PutMapping("/called/{id}")
    public String patientCalled(@PathVariable(name = "id") Integer id) {
        Patient patient = patientRepository.findPatientById(id);
        patient.setCalled(true);
        patientRepository.save(patient);
        return "redirect:/topPerformer";
    }

    @GetMapping("/{id}")
    public ModelAndView findPatientById(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("patient_id_search");
        Patient patient = patientRepository.findPatientById(id);
        mav.addObject("patient", patient);
        return mav;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatientById(@PathVariable(name = "id") Integer id) {
        patientRepository.deleteById(id);
        return "index";
    }

    @GetMapping("/find/{lastName}{firstName}")
    public Patient findPatientByLastNameAndFirstName(@PathVariable String lastName, @PathVariable String firstName) {
        return patientRepository.findPatientByLastNameAndFirstName(lastName, firstName);
    }

    @GetMapping("/find/{lastName}")
    public Patient findPatientByLastName(@PathVariable String lastName) {
        return patientRepository.findPatientByLastName(lastName);
    }
}

