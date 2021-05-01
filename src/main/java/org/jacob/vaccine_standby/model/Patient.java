package org.jacob.vaccine_standby.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Size(min = 3, max = 50)
    private String firstName;
    @Size(min = 3, max = 50)
    private String lastName;
    private boolean healthcareWorkerStatus;
    private String occupation;
    private String phoneNumber;
    private boolean minorityStatus;
    private double milesFromPharmacy;
    @Min(value = 18)
    @Max(value = 120)
    private String age;
    private boolean called;
    private double priorityScore;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public Patient() {
    }

    public Patient(String firstName, boolean healthcareWorkerStatus, String lastName, String occupation, String phoneNumber,
                   boolean minorityStatus, double distanceFromPharmacy, String age, boolean called, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.healthcareWorkerStatus = healthcareWorkerStatus;
        this.phoneNumber = phoneNumber;
        this.minorityStatus = minorityStatus;
        this.milesFromPharmacy = distanceFromPharmacy;
        this.age = age;
        this.called = called;
        this.location = location;
        assignPatientPriorityScore();
    }

    public void calculateDistanceFromPharmacy() {
        Location samplePharmacy = new Location();
        samplePharmacy.setLongitude(-84.389740);
        samplePharmacy.setLatitude(39.296750);
        setMilesFromPharmacy(location.distanceTo(samplePharmacy));
    }

    public void assignPatientPriorityScore() {
        calculateDistanceFromPharmacy();
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
        if (getOccupation().equals("Healthcare and Social Assistance")) {
            setHealthcareWorkerStatus(true);
            total += 1;
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

    public double getMilesFromPharmacy() {
        return milesFromPharmacy;
    }

    public void setMilesFromPharmacy(double distanceFromPharmacy) {
        this.milesFromPharmacy = distanceFromPharmacy;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean getCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
