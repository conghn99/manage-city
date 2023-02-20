package com.example.managecity.service;

import com.example.managecity.dto.EmployeeCertificationDTO;
import com.example.managecity.dto.Response;
import com.example.managecity.entity.Certification;
import com.example.managecity.entity.City;
import com.example.managecity.entity.Employee;
import com.example.managecity.entity.EmployeeCertification;
import com.example.managecity.exception.BadRequestException;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CertificationRepository;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.EmployeeCertificationRepository;
import com.example.managecity.request.UpsertEmployeeCertificationRequest;
import com.example.managecity.validate.EmployeeCertificationValidate;
import com.example.managecity.validate.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeCertificationService {
    private final EmployeeCertificationRepository employeeCertificationRepository;
    private final EmployeeCertificationValidate employeeCertificationValidate;
    private final CertificationRepository certificationRepository;
    private final CityRepository cityRepository;

    public List<EmployeeCertificationDTO> getAllEmployeeCertification() {
        return employeeCertificationRepository.getAll();
    }

    public EmployeeCertificationDTO getEmployeeCertificationById(Integer id) {
        return new EmployeeCertificationDTO(employeeCertificationRepository.getById(id));
    }

    public Response<EmployeeCertificationDTO> postEmployeeCertification(EmployeeCertificationDTO request) {
        ResponseStatus status = employeeCertificationValidate.validate(null,request);
        if(status!=ResponseStatus.OK){
            return new Response<>(status);
        }
        Employee employee = Employee.builder().id(request.getEmployeeId()).build();
        Certification certification = Certification.builder().id(request.getCertificationId()).build();
        City city = City.builder().id(request.getCityId()).build();
        EmployeeCertification employeeCertification = EmployeeCertification.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .employee(employee)
                .certification(certification)
                .city(city)
                .build();
        employeeCertificationRepository.save(employeeCertification);
        return new Response<>(new EmployeeCertificationDTO(employeeCertification));
    }

    public Response<EmployeeCertificationDTO> updateEmployeeCertification(Integer id, EmployeeCertificationDTO request) {
        EmployeeCertification employeeCertification = employeeCertificationRepository.getById(id);
        ResponseStatus status = employeeCertificationValidate.validate(id,request);
        if(status!=ResponseStatus.OK){
            return new Response<>(status);
        }
        Certification certification = certificationRepository.getById(request.getCertificationId());
        City city = cityRepository.getById(request.getCityId());
        employeeCertification.setStartDate(request.getStartDate());
        employeeCertification.setEndDate(request.getEndDate());
        employeeCertification.setCity(city);
        employeeCertification.setCertification(certification);
        employeeCertificationRepository.save(employeeCertification);
        return new Response<>(new EmployeeCertificationDTO(employeeCertification));
    }

    public void deleteEmployeeCertification(Integer id) {
        EmployeeCertification employeeCertification = employeeCertificationRepository.getById(id);
        employeeCertificationRepository.delete(employeeCertification);
    }
}
