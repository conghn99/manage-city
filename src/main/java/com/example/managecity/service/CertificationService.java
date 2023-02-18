package com.example.managecity.service;

import com.example.managecity.dto.CertificationDTO;
import com.example.managecity.entity.Certification;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CertificationRepository;
import com.example.managecity.request.UpsertCertificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {
    private final CertificationRepository certificationRepository;

    public List<CertificationDTO> getAllCertification() {
        return certificationRepository.getAllCertification();
    }

    public CertificationDTO getCertificationById(Integer id) {
        return new CertificationDTO(certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        }));
    }

    @Transactional
    public CertificationDTO postCertification(UpsertCertificationRequest request) {
        Certification certification = Certification.builder()
                .name(request.getName())
                .startEffectiveDate(request.getStartDate())
                .endEffectiveDate(request.getEndDate())
                .build();
        certificationRepository.save(certification);
        return new CertificationDTO(certification);
    }

    @Transactional
    public CertificationDTO updateCertification(Integer id, UpsertCertificationRequest request) {
        Certification certification = certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        });
        certification.setName(request.getName());
        certification.setStartEffectiveDate(request.getStartDate());
        certification.setEndEffectiveDate(request.getEndDate());
        certificationRepository.save(certification);
        return new CertificationDTO(certification);
    }

    @Transactional
    public void deleteCertification(Integer id) {
        Certification certification = certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        });
        certificationRepository.delete(certification);
    }
}
