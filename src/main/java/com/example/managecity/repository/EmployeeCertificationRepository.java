package com.example.managecity.repository;

import com.example.managecity.dto.EmployeeCertificationDTO;
import com.example.managecity.entity.EmployeeCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeCertificationRepository extends JpaRepository<EmployeeCertification, Integer> {
    EmployeeCertification getById(Integer id);

    @Query("select new com.example.managecity.dto.EmployeeCertificationDTO(ed) from EmployeeCertification ed")
    List<EmployeeCertificationDTO> getAll();

    @Query(value = "select new com.example.managecity.dto.EmployeeCertificationDTO(ed) from EmployeeCertification ed " +
            "where ed.employee.id = ?1 " +
            "and ed.certification.id = ?2 and ed.endDate >= current_date and ed.id <> ?3")
    List<EmployeeCertificationDTO>getDiplomaOfEmployee(Integer employeeId, Integer certificationId, Integer employeeCertificationId);
}
