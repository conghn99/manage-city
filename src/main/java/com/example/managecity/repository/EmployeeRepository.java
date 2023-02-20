package com.example.managecity.repository;

import com.example.managecity.dto.EmployeeDTO;
import com.example.managecity.dto.WardDTO;
import com.example.managecity.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByCode(String code);

    Employee getById(Integer id);

    @Query("select new com.example.managecity.dto.EmployeeDTO(ed) from Employee ed")
    List<EmployeeDTO> getAllEmployees();
}
