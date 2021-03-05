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
    private String lastName;
    private boolean healthcareWorkerStatus;
    private String occupation;
    private String phoneNumber;
    private boolean minorityStatus;
    private double milesFromPharmacy;
    private String age;
    private double priorityScore;

    public Patient() {

    }

    public Patient(String firstName, boolean healthcareWorkerStatus, String lastName, String occupation, String phoneNumber,
                   boolean minorityStatus, double distanceFromPharmacy, String age, double priorityScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.healthcareWorkerStatus = healthcareWorkerStatus;
        this.phoneNumber = phoneNumber;
        this.minorityStatus = minorityStatus;
        this.milesFromPharmacy = distanceFromPharmacy;
        this.age = age;
        this.priorityScore = priorityScore;
        assignPatientPriorityScore();
    }

    public void assignPatientPriorityScore() {
        double total = 0;
        if (getMilesFromPharmacy() < 1) {
            total += 6;
        }
        if (getMilesFromPharmacy() > 1 && getMilesFromPharmacy() < 5) {
            total += 3;
        }
        if (getMilesFromPharmacy() > 5) {
            total -= 3;
        }
        if (getHealthcareWorkerStatus()) {
            total += 2;
        }
        if (getMinorityStatus()) {
            total += 2;
        }
        total += (Double.parseDouble(getAge()) / 20.0);
        setPriorityScore(total);
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public double getMilesFromPharmacy() { return milesFromPharmacy; }

    public void setMilesFromPharmacy(double distanceFromPharmacy) {
        this.milesFromPharmacy = distanceFromPharmacy;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }
}
