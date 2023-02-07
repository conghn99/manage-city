package com.example.managecity.controller;

import com.example.managecity.request.UpsertDistrictRequest;
import com.example.managecity.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    // lay ra district list
    @GetMapping("")
    public ResponseEntity<?> getAllDistricts() {
        return ResponseEntity.ok(districtService.getAllDistrict());
    }

    // lay ra district theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable Integer id) {
        return ResponseEntity.ok(districtService.getDistrictById(id));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getAllDistrictsByCityId(@PathVariable Integer id) {
        return ResponseEntity.ok(districtService.getDistrictsByCityId(id));
    }

    // them district
    @PostMapping("")
    public ResponseEntity<?> addDistrict(@RequestBody UpsertDistrictRequest request) {
        return new ResponseEntity<>(districtService.postDistrict(request), HttpStatus.CREATED);
    }

    // sua district
    @PutMapping("{id}")
    public ResponseEntity<?> updateDistrict(@PathVariable Integer id, @RequestBody UpsertDistrictRequest request) {
        return ResponseEntity.ok(districtService.updateDistrict(id, request));
    }

    // xoa district
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}
