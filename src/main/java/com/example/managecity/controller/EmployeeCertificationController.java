package com.example.managecity.controller;

import com.example.managecity.request.UpsertCityRequest;
import com.example.managecity.request.UpsertEmployeeCertificationRequest;
import com.example.managecity.service.EmployeeCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee-certification")
public class EmployeeCertificationController {
    @Autowired
    private EmployeeCertificationService employeeCertificationService;

    @GetMapping("")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(employeeCertificationService.getAllEmployeeCertification());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeCertificationService.getEmployeeCertificationById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addCity(@RequestBody UpsertEmployeeCertificationRequest request) {
        return new ResponseEntity<>(employeeCertificationService.postEmployeeCertification(request), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        employeeCertificationService.deleteEmployeeCertification(id);
        return ResponseEntity.noContent().build();
    }
}
