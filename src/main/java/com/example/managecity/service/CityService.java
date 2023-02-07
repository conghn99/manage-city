package com.example.managecity.service;

import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.request.UpsertCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCityById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co city voi id = " + id);
        });
    }

    @Transactional
    public City postCity(UpsertCityRequest request) {
        City city = cityRepository.save(City.builder()
                .name(request.getName())
                .districts(request.getDistricts())
                .build());
        List<District> districts = request.getDistricts();
        for (District district : districts) {
            district.setCity(city);
            districtRepository.save(district);
        }
        return city;
    }

    @Transactional
    public City updateCity(Integer id, UpsertCityRequest request) {
        City city = cityRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co city voi id = " + id);
        });
        city.setName(request.getName());
        city.setDistricts(request.getDistricts());
        cityRepository.save(city);
        List<District> existingDistrict = request.getDistricts();
        return city;
    }

    @Transactional
    public void deleteCity(Integer id) {
        City city = cityRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co city voi id = " + id);
        });
        cityRepository.delete(city);
    }
}
