package com.example.managecity.service;

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

    public List<Certification> getAllCertification() {
        return certificationRepository.findAll();
    }

    public Certification getCertificationById(Integer id) {
        return certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        });
    }

    @Transactional
    public Certification postCertification(UpsertCertificationRequest request) {
        Certification certification = Certification.builder()
                .name(request.getName())
                .startEffectiveDate(request.getStartDate())
                .endEffectiveDate(request.getEndDate())
                .build();
        return certificationRepository.save(certification);
    }

    @Transactional
    public Certification updateCertification(Integer id, UpsertCertificationRequest request) {
        Certification certification = certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        });
        certification.setName(request.getName());
        certification.setStartEffectiveDate(request.getStartDate());
        certification.setEndEffectiveDate(request.getEndDate());
        return certificationRepository.save(certification);
    }

    @Transactional
    public void deleteCertification(Integer id) {
        Certification certification = certificationRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co certification voi id = " + id);
        });
        certificationRepository.delete(certification);
    }
}
