package com.example.managecity.repository;

import com.example.managecity.dto.CityDTO;
import com.example.managecity.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByName(String name);

    City getById(Integer id);

    @Query("SELECT new com.example.managecity.dto.CityDTO(ed) from City ed")
    List<CityDTO> getAllCities();
}
