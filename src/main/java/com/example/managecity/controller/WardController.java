package com.example.managecity.controller;

import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertWardRequest;
import com.example.managecity.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ward")
public class WardController {
    @Autowired
    private WardService wardService;

    // lay ra list ward
    @GetMapping("")
    public ResponseEntity<?> getAllWards() {
        return ResponseEntity.ok(wardService.getAllWard());
    }

    // lay ra ward theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getWardById(@PathVariable Integer id) {
        return ResponseEntity.ok(wardService.getWardById(id));
    }

    // them ward
    @PostMapping("")
    public ResponseEntity<?> addWard(@RequestBody UpsertWardRequest request) {
        return new ResponseEntity<>(wardService.postWard(request), HttpStatus.CREATED);
    }

    // sua ward
    @PutMapping("{id}")
    public ResponseEntity<?> updateWard(@PathVariable Integer id, @RequestBody UpsertWardRequest request) {
        return ResponseEntity.ok(wardService.updateWard(id, request));
    }

    // xoa ward
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWard(@PathVariable Integer id) {
        wardService.deleteWard(id);
        return ResponseEntity.noContent().build();
    }
}
