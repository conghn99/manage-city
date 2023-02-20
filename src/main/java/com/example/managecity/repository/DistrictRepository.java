package com.example.managecity.repository;

import com.example.managecity.dto.DistrictDTO;
import com.example.managecity.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM district d WHERE d.city_id = ?1")
    List<District> findDistrictsByCityId(Integer id);

    District getById(Integer id);

    @Query("SELECT new com.example.managecity.dto.DistrictDTO(ed) from District ed")
    List<DistrictDTO> getAllDistricts();
}
