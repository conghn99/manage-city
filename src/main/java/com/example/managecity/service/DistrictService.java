package com.example.managecity.service;

import com.example.managecity.dto.DistrictDTO;
import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
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

    public List<District> getAllDistrict() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Integer id) {
        return districtRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co district voi id = " + id);
        });
    }

    @Transactional
    public District postDistrict(UpsertDistrictRequest request) {
        City city = cityRepository.findById(request.getCityId()).orElseThrow(() -> {
            throw new NotFoundException("Ko co city voi id = " + request.getCityId());
        });
        District district = District.builder()
                .name(request.getName())
                .city(city)
                .build();
        return districtRepository.save(district);
    }

    @Transactional
    public District updateDistrict(Integer id, UpsertDistrictRequest request) {
        District district = districtRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co district voi id = " + id);
        });
        City city = cityRepository.findById(request.getCityId()).orElseThrow(() -> {
            throw new NotFoundException("Ko co city voi id = " + request.getCityId());
        });
        district.setName(request.getName());
        district.setCity(city);
        return districtRepository.save(district);
    }

    @Transactional
    public void deleteDistrict(Integer id) {
        District district = districtRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co district voi id = " + id);
        });
        districtRepository.delete(district);
    }

    public List<DistrictDTO> getDistrictsByCityId(Integer id) {
        List<District> districts = districtRepository.findDistrictsByCityId(id);
        List<DistrictDTO> dtoDistricts = new ArrayList<>();
        districts.forEach(district -> {
            DistrictDTO districtDTO = convertToDTO(district);
            dtoDistricts.add(districtDTO);
        });
        return dtoDistricts;
    }

    private DistrictDTO convertToDTO(District district) {
        DistrictDTO dto = new DistrictDTO();
        dto.setId(district.getId());
        dto.setName(district.getName());
        return dto;
    }
}
