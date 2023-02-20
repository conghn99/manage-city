package com.example.managecity.validate;

import com.example.managecity.dto.EmployeeCertificationDTO;
import com.example.managecity.entity.EmployeeCertification;
import com.example.managecity.repository.CertificationRepository;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.EmployeeCertificationRepository;
import com.example.managecity.repository.EmployeeRepository;
import com.example.managecity.request.UpsertEmployeeCertificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeCertificationValidate {
    private final EmployeeCertificationRepository employeeCertificationRepository;

    public ResponseStatus validate(Integer employeeCertificationId, EmployeeCertificationDTO request) {
        return validateCertificateNumberOfEachEmployee(request.getEmployeeId(), request.getCertificationId(), employeeCertificationId);
    }

    public ResponseStatus validateCertificateNumberOfEachEmployee(Integer employeeId, Integer certificationId, Integer employeeCertificationId) {
        if (employeeCertificationId == null) {
            employeeCertificationId = new EmployeeCertification().getId();
        }
        List<EmployeeCertificationDTO> certifications = employeeCertificationRepository.getDiplomaOfEmployee(employeeId, certificationId, employeeCertificationId);
        if (certifications.size() >= 3) {
            return ResponseStatus.HAVE_3_CERTIFICATE;
        }
        for (EmployeeCertificationDTO value : certifications) {
            if (value.getCertificationId().equals(certificationId)) ;
            return ResponseStatus.CERTIFICATE_ALREADY_EXIST;
        }
        return ResponseStatus.OK;
    }
}
