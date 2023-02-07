package com.example.managecity.controller;

import com.example.managecity.request.UpsertCityRequest;
import com.example.managecity.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
public class CityController {
    @Autowired
    private CityService cityService;

    // lay ra list city
    @GetMapping("")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCity());
    }

    // lay ra city theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    // them city
    @PostMapping("")
    public ResponseEntity<?> addCity(@RequestBody UpsertCityRequest request) {
        return new ResponseEntity<>(cityService.postCity(request), HttpStatus.CREATED);
    }

    // sua city
    @PutMapping("{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody UpsertCityRequest request) {
        return ResponseEntity.ok(cityService.updateCity(id, request));
    }

    // xoa city
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
