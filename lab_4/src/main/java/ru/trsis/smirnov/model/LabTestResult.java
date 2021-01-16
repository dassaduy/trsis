package ru.trsis.smirnov.model;

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

    @Transient
    private String patient;

//    @Column(name = "PATIENT_ID")
//    private Patient patient_id;

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
        return "LabTestResult{" +
                "id=" + id +
                ", patient='" + patient + '\'' +
//                ", patient_id=" + patient_id +
                ", date=" + date +
                ", hemoglobin=" + hemoglobin +
                ", glucose=" + glucose +
                ", alt=" + alt +
                ", ast=" + ast +
                '}';
    }
}
