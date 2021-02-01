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
    private String healthcareWorkerStatus;
    private String lastName;
    private String phoneNumber;
    private String race;
    private double distanceFromPharmacy;
    private int age;
    private double priorityScore;

    public Patient() {

    }

    public Patient(String firstName, String healthcareWorkerStatus, String lastName, String phoneNumber, String race, double distanceFromPharmacy, int age, double priorityScore) {
        this.firstName = firstName;
        this.healthcareWorkerStatus = healthcareWorkerStatus;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.race = race;
        this.distanceFromPharmacy = distanceFromPharmacy;
        this.age = age;
        this.priorityScore = priorityScore;
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

    public String getHealthcareWorkerStatus() {
        return healthcareWorkerStatus;
    }

    public void setHealthcareWorkerStatus(String healthcareWorkerStatus) {
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getDistanceFromPharmacy() {
        return distanceFromPharmacy;
    }

    public void setDistanceFromPharmacy(double distanceFromPharmacy) {
        this.distanceFromPharmacy = distanceFromPharmacy;
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
