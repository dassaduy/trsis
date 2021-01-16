package ru.trsis.smirnov.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PATIENTS")
public class Patient {

    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private int id;

    @JoinColumn(name = "PATIENT_ID")
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LabTestResult> labTestResults;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "LOCATION")
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LabTestResult> getLabTestResults() {
        return labTestResults;
    }

    public void setLabTestResults(List<LabTestResult> labTestResults) {
        this.labTestResults = labTestResults;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", labTestResults=" + labTestResults +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && Objects.equals(labTestResults, patient.labTestResults)
                && Objects.equals(firstName, patient.firstName) && Objects.equals(secondName, patient.secondName)
                && Objects.equals(middleName, patient.middleName) && Objects.equals(birthday, patient.birthday)
                && Objects.equals(location, patient.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, labTestResults, firstName, secondName, middleName, birthday, location);
    }
}
