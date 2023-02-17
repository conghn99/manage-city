package com.example.managecity.controller;

import com.example.managecity.entity.Employee;
import com.example.managecity.exception.BadRequestException;
import com.example.managecity.request.UpdateAddressRequest;
import com.example.managecity.request.UpsertEmployeeRequest;
import com.example.managecity.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public ResponseEntity<?> addEmployee(@Valid @RequestBody UpsertEmployeeRequest request) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UpsertEmployeeRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errorMessage = violations.iterator().next().getMessage();
            return ResponseEntity.badRequest().body(new BadRequestException(errorMessage));
        }
        return new ResponseEntity<>(employeeService.addEmployee(request), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateAddressEmployee(@PathVariable Integer id, @RequestBody UpdateAddressRequest request) {
        return ResponseEntity.ok(employeeService.updateAddress(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
