package com.example.managecity.service;

import com.example.managecity.entity.Employee;
import com.example.managecity.repository.EmployeeRepository;
import com.example.managecity.request.UpsertEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(UpsertEmployeeRequest request) {
        Employee employee = Employee.builder()
                .code(request.getCode())
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .phone(request.getPhone())
                .build();
        return employeeRepository.save(employee);
    }
}
