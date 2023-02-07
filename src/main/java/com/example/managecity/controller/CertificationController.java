package com.example.managecity.controller;

import com.example.managecity.request.UpsertCertificationRequest;
import com.example.managecity.request.UpsertCityRequest;
import com.example.managecity.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/certification")
public class CertificationController {
    @Autowired
    private CertificationService certificationService;

    // lay ra list certification
    @GetMapping("")
    public ResponseEntity<?> getAllCertifications() {
        return ResponseEntity.ok(certificationService.getAllCertification());
    }

    // lay ra certification theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getCertificationById(@PathVariable Integer id) {
        return ResponseEntity.ok(certificationService.getCertificationById(id));
    }

    // them certification
    @PostMapping("")
    public ResponseEntity<?> addCertification(@RequestBody UpsertCertificationRequest request) {
        return new ResponseEntity<>(certificationService.postCertification(request), HttpStatus.CREATED);
    }

    // sua certification
    @PutMapping("{id}")
    public ResponseEntity<?> updateCertification(@PathVariable Integer id, @RequestBody UpsertCertificationRequest request) {
        return ResponseEntity.ok(certificationService.updateCertification(id, request));
    }

    // xoa certification
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCertification(@PathVariable Integer id) {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}
