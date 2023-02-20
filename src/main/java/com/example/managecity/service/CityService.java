package com.example.managecity.service;

import com.example.managecity.dto.CityDTO;
import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Ward;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public List<CityDTO> getAllCity() {
        return cityRepository.getAllCities();
    }

    public CityDTO getCityById(Integer id) {
        return new CityDTO(cityRepository.getById(id));
    }

    @Transactional
    public CityDTO postCity(UpsertCityRequest request) {
        City city = City.builder()
                .name(request.getName())
                .districts(request.getDistricts())
                .build();
        cityRepository.save(city);
        for (District district : request.getDistricts()) {
            District dist = District.builder()
                    .name(district.getName())
                    .city(city)
                    .build();
            districtRepository.save(dist);
            for (Ward ward : district.getWards()) {
                Ward wa = Ward.builder()
                        .name(ward.getName())
                        .district(dist)
                        .build();
                wardRepository.save(wa);
            }
        }
        return new CityDTO(city);
    }

    @Transactional
    public CityDTO updateCity(Integer id, UpsertCityRequest request) {
        City city = cityRepository.getById(id);
        city.setName(request.getName());
        cityRepository.save(city);
        for (District d : request.getDistricts()) {
            District updateDis = districtRepository.findById(d.getId()).orElseThrow(() -> {
                throw new NotFoundException("Ko co district voi id = " + d.getId());
            });
            updateDis.setName(d.getName());
            updateDis.setCity(city);
            districtRepository.save(updateDis);
        }
        return new CityDTO(city);
    }

    @Transactional
    public void deleteCity(Integer id) {
        City city = cityRepository.getById(id);
        cityRepository.delete(city);
    }
}
