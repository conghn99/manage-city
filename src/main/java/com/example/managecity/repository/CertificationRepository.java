package com.example.managecity.repository;

import com.example.managecity.dto.CertificationDTO;
import com.example.managecity.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    @Query("select new com.example.managecity.dto.CertificationDTO(ed) from Certification ed")
    List<CertificationDTO> getAllCertification();

    Certification getById(Integer id);
}

