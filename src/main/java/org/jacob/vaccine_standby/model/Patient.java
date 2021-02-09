package org.jacob.vaccine_standby.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private boolean healthcareWorkerStatus;
    private String lastName;
    private String phoneNumber;
    private boolean minorityStatus;
    private double milesFromPharmacy;
    private int age;
    private double priorityScore;

    public Patient() {

    }

    public Patient(String firstName, boolean healthcareWorkerStatus, String lastName, String phoneNumber, boolean minorityStatus, double distanceFromPharmacy, int age, double priorityScore) {
        this.firstName = firstName;
        this.healthcareWorkerStatus = healthcareWorkerStatus;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.minorityStatus = minorityStatus;
        this.milesFromPharmacy = distanceFromPharmacy;
        this.age = age;
        this.priorityScore = priorityScore;
    }

    public void assignPatientPriorityScore(Patient patient) {
        double total = 0;
        if (patient.getMilesFromPharmacy() < 1) {
            total += 6;
        }
        if (patient.getMilesFromPharmacy() > 1 && patient.getMilesFromPharmacy() < 5) {
            total += 3;
        }
        if (patient.getMilesFromPharmacy() > 5) {
            total -= 3;
        }
        if (patient.getHealthcareWorkerStatus()) {
            total += 2;
        }
        if (patient.getMinorityStatus()) {
            total += 2;
        }
        total += (patient.getAge() / 20.0);
        patient.setPriorityScore(total);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean getHealthcareWorkerStatus() {
        return healthcareWorkerStatus;
    }

    public void setHealthcareWorkerStatus(boolean healthcareWorkerStatus) {
        this.healthcareWorkerStatus = healthcareWorkerStatus;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getMinorityStatus() {
        return minorityStatus;
    }

    public void setMinorityStatus(boolean minorityStatus) {
        this.minorityStatus = minorityStatus;
    }

    public double getMilesFromPharmacy() {
        return milesFromPharmacy;
    }

    public void setMilesFromPharmacy(double distanceFromPharmacy) {
        this.milesFromPharmacy = distanceFromPharmacy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }
}
