package ru.trsis.smirnov.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.trsis.smirnov.repositories.model.LabTestResult;

public interface LabTestRepository extends CrudRepository<LabTestResult, Integer> {

}
