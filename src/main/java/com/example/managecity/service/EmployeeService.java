package com.example.managecity.service;

import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Employee;
import com.example.managecity.entity.Ward;
import com.example.managecity.exception.BadRequestException;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.EmployeeRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpdateAddressRequest;
import com.example.managecity.request.UpsertEmployeeRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee addEmployee(UpsertEmployeeRequest request) {
        if (employeeRepository.findByCode(request.getCode()) != null) {
            throw new BadRequestException("code must be unique");
        }
        City city = cityRepository.findByName(request.getCity());
        District district = districtRepository.findByName(request.getDistrict());
        Ward ward = wardRepository.findByName(request.getWard());
        List<District> diLi = city.getDistricts();
        if (diLi.contains(district)) {
            List<Ward> waLi = district.getWards();
            if (!waLi.contains(ward)) {
                throw new BadRequestException(district.getName() + " district not have " + request.getWard());

            }

            Employee employee = Employee.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .email(request.getEmail())
                    .age(request.getAge())
                    .phone(request.getPhone())
                    .build();
            return employeeRepository.save(employee);

        } else {
            throw new BadRequestException(city.getName() + " city not have " + request.getDistrict());
        }
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co employee voi id = " + id);
        });
        employeeRepository.delete(employee);
    }

    public Employee updateAddress(Integer id, UpdateAddressRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co employee voi id = " + id);
        });
        if(!request.getCity().trim().equals("") && !request.getDistrict().trim().equals("") && !request.getWard().trim().equals("")) {
            employee.setCity(request.getCity());
            employee.setDistrict(request.getDistrict());
            employee.setWard(request.getWard());
            employeeRepository.save(employee);
        }
        return employee;
    }
}
