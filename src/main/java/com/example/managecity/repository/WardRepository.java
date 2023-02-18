package com.example.managecity.repository;

import com.example.managecity.dto.WardDTO;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM ward w WHERE w.district_id = ?1")
    List<Ward> findWardsByDistrictId(Integer id);

    Ward findByName(String name);

    @Query("select new com.example.managecity.dto.WardDTO(ed) from Ward ed")
    List<WardDTO> getAllWards();
}
