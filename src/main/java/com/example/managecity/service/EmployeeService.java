package com.example.managecity.service;

import com.example.managecity.dto.EmployeeDTO;
import com.example.managecity.dto.Response;
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
import com.example.managecity.validate.EmployeeValidate;
import com.example.managecity.validate.ResponseStatus;
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
    private final EmployeeValidate employeeValidate;

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.getAllEmployees();
    }

    @Transactional
    public Response<EmployeeDTO> addEmployee(UpsertEmployeeRequest request) {
        ResponseStatus status = employeeValidate.validate(request);
        if(status != ResponseStatus.OK){
            return new Response<>(status);
        }

        City city = cityRepository.getById(request.getCityId());
        District district = districtRepository.getById(request.getDistrictId());
        Ward ward = wardRepository.getById(request.getWardId());

        Employee employee = Employee.builder()
                .code(request.getCode())
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .phone(request.getPhone())
                .city(city)
                .district(district)
                .ward(ward)
                .build();
        employeeRepository.save(employee);
        return new Response<>(new EmployeeDTO(employee));
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.getById(id);
        employeeRepository.delete(employee);
    }

    public Response<EmployeeDTO> updateEmployee(Integer id, UpsertEmployeeRequest request) {
        Employee employee = employeeRepository.getById(id);
        ResponseStatus status = employeeValidate.validate(request);
        if(status != ResponseStatus.OK){
            return new Response<>(status);
        }

        City city = cityRepository.getById(request.getCityId());
        District district = districtRepository.getById(request.getDistrictId());
        Ward ward = wardRepository.getById(request.getWardId());

        employee.setCode(request.getCode());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAge(request.getAge());
        employee.setCity(city);
        employee.setDistrict(district);
        employee.setWard(ward);
        employeeRepository.save(employee);

        return new Response<>(new EmployeeDTO(employee));
    }
}
