package ru.trsis.smirnov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trsis.smirnov.dao.LabTestRepository;
import ru.trsis.smirnov.dao.PatientsRepository;
import ru.trsis.smirnov.model.LabTestResult;
import ru.trsis.smirnov.model.Patient;

import java.util.ArrayList;
import java.util.List;

@Service()
public class PatientsService {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository, LabTestRepository labTestRepository) {
        this.patientsRepository = patientsRepository;
    }

    void addPatientFromResult(LabTestResult result) {
        Patient patient = findPatient(result.getPatient());

        if (patient == null) {
            patient = new Patient();
            String[] names = result.getPatient().split(" ");
            patient.setSecondName(names[0]);
            patient.setFirstName(names.length > 1 ? names[1] : null);
            patient.setMiddleName(names.length > 2 ? names[2] : null);
            patient.setLabTestResults(new ArrayList<>());
        }

        patient.getLabTestResults().add(result);
        patientsRepository.save(patient);
    }

    Patient findPatient(String patientName) {
        Patient found = null;
        for (Patient patient : patientsRepository.findAll()) {
            String name = (patient.getSecondName() == null) ? "" : patient.getSecondName()
                    + (patient.getFirstName() == null ? "" : patient.getFirstName()
                    + (patient.getMiddleName() == null ? "" : patient.getMiddleName()));
            if (name.equals(patientName.replace(" ", ""))) {
                found = patient;
                break;
            }
        }
        return found;
    }

    public List<LabTestResult> findAll() {
        List<LabTestResult> labTestResults = new ArrayList<>();
        patientsRepository.findAll().forEach(patient -> {
            String name = (patient.getSecondName() == null) ? "" : patient.getSecondName() + " "
                    + (patient.getFirstName() == null ? "" : patient.getFirstName()  + " "
                    + (patient.getMiddleName() == null ? "" : patient.getMiddleName()));
            List<LabTestResult> testResults = patient.getLabTestResults();
            testResults.forEach(result -> {
                result.setPatient(name);
                labTestResults.add(result);
            });
        });
        return labTestResults;
    }
}
