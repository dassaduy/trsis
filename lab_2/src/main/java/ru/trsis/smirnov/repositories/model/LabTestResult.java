package ru.trsis.smirnov.repositories.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "LAB_RESULTS")
public class LabTestResult {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PATIENT")
    private String patient;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "HEMOGLOBIN")
    private int hemoglobin;

    @Column(name = "GLUCOSE")
    private float glucose;

    @Column(name = "ALT")
    private int alt;

    @Column(name = "AST")
    private int ast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(int hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public float getGlucose() {
        return glucose;
    }

    public void setGlucose(float glucose) {
        this.glucose = glucose;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    @Override
    public String toString() {
        return "LabTestResults{" +
                "id=" + id +
                ", patient='" + patient + '\'' +
                ", date=" + date +
                ", hemoglobin=" + hemoglobin +
                ", glucose=" + glucose +
                ", alt=" + alt +
                ", ast=" + ast +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabTestResult that = (LabTestResult) o;
        return id == that.id && hemoglobin == that.hemoglobin && Float.compare(that.glucose, glucose) == 0
                && alt == that.alt && ast == that.ast && Objects.equals(patient, that.patient)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, date, hemoglobin, glucose, alt, ast);
    }
}
