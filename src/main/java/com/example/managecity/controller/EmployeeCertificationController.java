package com.example.managecity.controller;

import com.example.managecity.dto.EmployeeCertificationDTO;
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
    public ResponseEntity<?> getAllEmployeeCertification() {
        return ResponseEntity.ok(employeeCertificationService.getAllEmployeeCertification());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeCertificationById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeCertificationService.getEmployeeCertificationById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployeeCertification(@RequestBody EmployeeCertificationDTO request) {
        return new ResponseEntity<>(employeeCertificationService.postEmployeeCertification(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployeeCertification(@PathVariable Integer id, @RequestBody EmployeeCertificationDTO request) {
        return ResponseEntity.ok(employeeCertificationService.updateEmployeeCertification(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployeeCertification(@PathVariable Integer id) {
        employeeCertificationService.deleteEmployeeCertification(id);
        return ResponseEntity.noContent().build();
    }
}
