package ru.trsis.smirnov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trsis.smirnov.model.LabTestResult;
import ru.trsis.smirnov.services.LabTestService;

import java.text.ParseException;

@RestController
public class LabTestController {

    private final LabTestService labTestService;

    @Autowired
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }

    @RequestMapping(value = "/all_results", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getAllResults(@RequestParam(value = "id", required = false) Integer id,
                                           @RequestParam(value = "patient", required = false) String patient,
                                           @RequestParam(value = "date", required = false) String date)
            throws ParseException {
        return ResponseEntity.ok(labTestService.searchResults(id, patient, date));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveResult(@RequestBody LabTestResult result) {
        labTestService.addResult(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> deleteResult(@RequestBody LabTestResult result) {
        labTestService.deleteResult(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
