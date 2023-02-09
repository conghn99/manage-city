package com.example.managecity.controller;

import com.example.managecity.entity.Employee;
import com.example.managecity.request.UpsertEmployeeRequest;
import com.example.managecity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<?> getEmployeeList() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody UpsertEmployeeRequest request) {
        return new ResponseEntity<>(employeeService.addEmployee(request), HttpStatus.CREATED);
    }
}
