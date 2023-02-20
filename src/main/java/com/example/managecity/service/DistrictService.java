package com.example.managecity.service;

import com.example.managecity.dto.DistrictDTO;
import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Ward;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertDistrictRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final WardRepository wardRepository;

    public List<DistrictDTO> getAllDistrict() {
        return districtRepository.getAllDistricts();
    }

    public DistrictDTO getDistrictById(Integer id) {
        return new DistrictDTO(districtRepository.getById(id));
    }

    @Transactional
    public DistrictDTO postDistrict(UpsertDistrictRequest request) {
        City city = cityRepository.getById(request.getCityId());
        District district = District.builder()
                .name(request.getName())
                .city(city)
                .build();
        for (Ward ward : request.getWards()) {
            ward.setDistrict(district);
            wardRepository.save(ward);
        }
        districtRepository.save(district);
        return new DistrictDTO(district);
    }

    @Transactional
    public DistrictDTO updateDistrict(Integer id, UpsertDistrictRequest request) {
        District district = districtRepository.getById(id);
        City city = cityRepository.getById(request.getCityId());
        district.setName(request.getName());
        district.setCity(city);
        districtRepository.save(district);
        for (Ward w : request.getWards()) {
            Ward updateWard = wardRepository.getById(w.getId());
            updateWard.setName(w.getName());
            updateWard.setDistrict(district);
            wardRepository.save(updateWard);
        }
        return new DistrictDTO(district);
    }

    @Transactional
    public void deleteDistrict(Integer id) {
        District district = districtRepository.getById(id);
        districtRepository.delete(district);
    }

    public List<District> getDistrictsByCityId(Integer id) {
        return districtRepository.findDistrictsByCityId(id);
    }
}
