package com.example.managecity.service;

import com.example.managecity.dto.EmployeeCertificationDTO;
import com.example.managecity.entity.Certification;
import com.example.managecity.entity.City;
import com.example.managecity.entity.Employee;
import com.example.managecity.entity.EmployeeCertification;
import com.example.managecity.exception.BadRequestException;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.EmployeeCertificationRepository;
import com.example.managecity.request.UpsertEmployeeCertificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeCertificationService {
    private final EmployeeCertificationRepository employeeCertificationRepository;

    public List<EmployeeCertificationDTO> getAllEmployeeCertification() {
        return employeeCertificationRepository.getAll();
    }

    public EmployeeCertificationDTO getEmployeeCertificationById(Integer id) {
        return new EmployeeCertificationDTO(employeeCertificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found with id = " + id);
        }));
    }

    public EmployeeCertificationDTO postEmployeeCertification(UpsertEmployeeCertificationRequest request) {
        List<EmployeeCertificationDTO> diplomas = employeeCertificationRepository.getDiplomaOfEmployee(request.getEmployeeId(), request.getCertificationId(), request.getId());
        if (diplomas.size() >= 3) {
            throw new BadRequestException("Can't have more than 3 effective certification at the same time");
        }
        for (EmployeeCertificationDTO value : diplomas) {
            if (value.getCertificationId().equals(request.getCertificationId())) {
                throw new BadRequestException("Can't have more than 1 effective certification provided by the same city at the same time");
            }
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
        return new EmployeeCertificationDTO(employeeCertification);
    }

    public void deleteEmployeeCertification(Integer id) {
        EmployeeCertification employeeCertification = employeeCertificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found with id = " + id);
        });
        employeeCertificationRepository.delete(employeeCertification);
    }
}
