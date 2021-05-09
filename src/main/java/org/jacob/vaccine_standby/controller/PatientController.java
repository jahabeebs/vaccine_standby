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

/**
 * Provides both the page routing and service logic
 */
@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    List<Patient> patientsComingList = new ArrayList<>();

    /**
     * Routes to a blank waiting list form, the base page of the project
     *
     * @param model
     * @return "index"
     */
    @GetMapping("/")
    public String showPatientForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "index";
    }

    @GetMapping("/error")
    public String pageNotFound() {
        return "error";
    }

    /**
     * Provides a simple UI to reach pages a respondent wouldn't see (like the list of coming patients)
     *
     * @return "admin_panel"
     */
    @GetMapping("/adminPanel")
    public String adminPanel() {
        return "admin_panel";
    }

    /**
     * Upon form submission this registers a new patient, assigns a priority score and saves to the in-memory database
     *
     * @param patient
     * @return "registration_successful"
     */
    @PostMapping("/register")
    public String submitPatientForm(@ModelAttribute("patient") Patient patient) {
        patient.assignPatientPriorityScore();
        patientRepository.save(patient);
        return "registration_successful";
    }

    /**
     * Provides the list of patients added from topPatient or topMissed
     *
     * @param model
     * @return "no_results_found" or "list_of_patients_coming"
     */
    @GetMapping("/listComingPatients")
    public String listComingPatients(Model model) {
        if (patientsComingList.isEmpty()) {
            return "no_results_found";
        }
        model.addAttribute("patientsComingList", patientsComingList);
        return "list_of_patients_coming";
    }

    /**
     * Adds a patient to the list of coming patients and deletes the patient from the database
     *
     * @param id
     * @return "redirect:/listComingPatients"
     */
    @PutMapping("/patientsComing/{id}")
    public String patientsComing(@PathVariable(name = "id") Integer id) {
        Patient patient = patientRepository.findPatientById(id);
        if (!patientsComingList.contains(patient)) {
            patientsComingList.add(patient);
            patientRepository.deleteById(patient.getId());
        }
        return "redirect:/listComingPatients";
    }

    /**
     * Provides the patient with the highest priority score who hasn't been called
     *
     * @return "highest_priority_patient" or "no_results_found"
     */
    @GetMapping("/topPatient")
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

    /**
     * Provides the patient with the highest priority score who has been called once already
     *
     * @return "highest_priority_missed_patient" or "no_results_found"
     */
    @GetMapping("/topMissed")
    public ModelAndView showTopMissed() {
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

    /**
     * Marks a patient as having been called and updates the database accordingly
     *
     * @param id
     * @return
     */
    @PutMapping("/called/{id}")
    public String patientCalled(@PathVariable(name = "id") Integer id) {
        Patient patient = patientRepository.findPatientById(id);
        patient.setCalled(true);
        patientRepository.save(patient);
        return "redirect:/topPatient";
    }

    /**
     * Allows for searching individual IDs to see if one is populated with a patient
     *
     * @param id
     * @return "patient_id_search" or "no_results_found"
     */
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

    /**
     * Directly deletes a patient by ID. Used for final deletion of a patient from the lost of coming patients
     *
     * @param id
     * @return "redirect:/"
     */
    @DeleteMapping("/delete/{id}")
    public String deletePatientById(@PathVariable(name = "id") Integer id) {
        if (!patientsComingList.isEmpty()) {
            patientsComingList.removeIf(patient -> (id == patient.getId()));
        }
        patientRepository.deleteById(id);
        return "redirect:/";
    }

    /**
     * Removes a patient from the list of coming patients
     *
     * @param id
     * @return "redirect:/listComingPatients"
     */
    @DeleteMapping("/deleteFromComingList/{id}")
    public String deletePatientFromComingList(@PathVariable(name = "id") Integer id) {
        if (!patientsComingList.isEmpty()) {
            patientsComingList.removeIf(patient -> (id == patient.getId()));
        }
        return "redirect:/listComingPatients";
    }
}

