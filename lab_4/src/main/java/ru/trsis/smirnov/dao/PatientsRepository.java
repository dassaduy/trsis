package ru.trsis.smirnov.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trsis.smirnov.model.Patient;

public interface PatientsRepository extends CrudRepository<Patient, Integer> {

}
