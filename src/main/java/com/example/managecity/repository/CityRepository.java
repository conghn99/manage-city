package com.example.managecity.repository;

import com.example.managecity.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByName(String name);
}
