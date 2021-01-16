package ru.trsis.smirnov.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trsis.smirnov.model.LabTestResult;


public interface LabTestRepository extends CrudRepository<LabTestResult, Integer> {

}
