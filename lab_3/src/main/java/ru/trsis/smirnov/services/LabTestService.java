package ru.trsis.smirnov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trsis.smirnov.dao.LabTestRepository;
import ru.trsis.smirnov.model.LabTestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class LabTestService {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final LabTestRepository labTestRepository;

    @Autowired
    public LabTestService(LabTestRepository labTestRepository) {
        this.labTestRepository = labTestRepository;
    }

    public List<LabTestResult> searchResults(Integer id, String patient, String date) {

        List<LabTestResult> labTestResults = new ArrayList<>();
        labTestRepository.findAll().forEach(labTestResults::add);
        labTestResults = labTestResults.stream()
                .filter(result -> id == null || result.getId() == id)
                .filter(result -> patient == null || patient.equals("") || result.getPatient().contains(patient))
                .filter(result -> date == null || date.equals("")
                        || DATE_FORMAT.format(result.getDate()).equals(date.substring(0, "yyyy-MM-dd".length())))
                .collect(Collectors.toList());
        return labTestResults;
    }

    public void deleteResult(LabTestResult result) {
        labTestRepository.delete(result);
    }

    public void addResult(LabTestResult result) {
        result.setDate(new Date());
        labTestRepository.save(result);
    }
}
