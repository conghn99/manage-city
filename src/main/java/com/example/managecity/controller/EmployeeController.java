package com.example.managecity.controller;

import com.example.managecity.dto.EmployeeDTO;
import com.example.managecity.request.UpsertEmployeeRequest;
import com.example.managecity.service.EmployeeService;
import com.example.managecity.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ImportExcelService importExcelService;

    @GetMapping("")
    public ResponseEntity<?> getEmployeeList() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody UpsertEmployeeRequest request) {
        return new ResponseEntity<>(employeeService.addEmployee(request), HttpStatus.CREATED);
    }

    @PostMapping("/excel")
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(importExcelService.importExcel(file));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAddressEmployee(@PathVariable Integer id, @RequestBody UpsertEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
