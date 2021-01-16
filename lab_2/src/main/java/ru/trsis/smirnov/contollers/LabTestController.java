package ru.trsis.smirnov.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.trsis.smirnov.repositories.model.LabTestResult;
import ru.trsis.smirnov.services.LabTestService;

import java.util.Date;
import java.util.Map;

@Controller
public class LabTestController {

    private final LabTestService labTestService;

    @Autowired
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }

    @GetMapping("/")
    @ResponseBody
    public String getIndex() {
        return "index";
    }

    @GetMapping("/all_results")
    public ResponseEntity<?> getAllResults(@RequestParam(value = "id", required = false) Integer id,
                                           @RequestParam(value = "patient", required = false) String patient,
                                           @RequestParam(value = "date", required = false) Date date) {
        return ResponseEntity.ok(labTestService.searchResults(id, patient, date));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveResult(@ModelAttribute LabTestResult result) {
        labTestService.addResult(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteResult(@ModelAttribute LabTestResult result) {
        labTestService.deleteResult(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
