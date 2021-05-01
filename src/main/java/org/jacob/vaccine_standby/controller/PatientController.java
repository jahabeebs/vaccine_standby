package org.jacob.vaccine_standby.controller;

import org.jacob.vaccine_standby.model.Patient;
import org.jacob.vaccine_standby.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    List<Patient> patientsComingList = new ArrayList<>();

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
        return "registration_successful";
    }

    @GetMapping("/listComingPatients")
    public String listComingPatients(Model model) {
        model.addAttribute("patientsComingList", patientsComingList);
        return "list_of_patients_coming";
    }

    @PostMapping("/patientsComing")
    public String patientsComing(@ModelAttribute("patient") Patient patient) {
        patientsComingList.add(patient);
        patientRepository.delete(patient);
        return "highest_priority_missed_patient";
    }

    @GetMapping("/topPerformer")
    public ModelAndView showTop() {
        ModelAndView mav = new ModelAndView("highest_priority_patient");
        Patient patient = patientRepository.findTopByCalledFalseOrderByPriorityScoreDesc();
        if (patient == null) {
            ModelAndView mav2 = new ModelAndView("no_results_found");
            return mav2;
        } else {
            mav.addObject("patient", patient);
        }
        return mav;
    }

    @GetMapping("/topMissed")
    public ModelAndView callAttemptEnforcer() {
        ModelAndView mav = new ModelAndView("highest_priority_missed_patient");
        Patient patient = patientRepository.findTopByCalledTrueOrderByPriorityScoreDesc();
        if (patient == null) {
            ModelAndView mav2 = new ModelAndView("no_results_found");
            return mav2;
        } else {
            mav.addObject("patient", patient);
        }
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
        if (patient == null) {
            ModelAndView mav2 = new ModelAndView("no_results_found");
            return mav2;
        } else {
            mav.addObject("patient", patient);
        }
        return mav;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatientById(@PathVariable(name = "id") Integer id) {
        patientRepository.deleteById(id);
        return "redirect:/";
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

