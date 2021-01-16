package ru.trsis.smirnov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trsis.smirnov.repositories.LabTestRepository;
import ru.trsis.smirnov.repositories.model.LabTestResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class LabTestService {

    private final LabTestRepository labTestRepository;

    @Autowired
    public LabTestService(LabTestRepository labTestRepository) {
        this.labTestRepository = labTestRepository;
    }

    public List<LabTestResult> searchResults(Integer id, String name, Date date) {
        ArrayList<LabTestResult> labTestResults = new ArrayList<>();
        labTestRepository.findAll().forEach(labTestResults::add);
        return labTestResults;
    }

    public void deleteResult(LabTestResult result) {
        labTestRepository.delete(result);
    }

    public void addResult(LabTestResult result) {
        labTestRepository.save(result);
    }
}
