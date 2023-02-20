package com.example.managecity.service;

import com.example.managecity.dto.DistrictDTO;
import com.example.managecity.dto.WardDTO;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Ward;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertWardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WardService {
    private final WardRepository wardRepository;
    private final DistrictRepository districtRepository;

    public List<WardDTO> getAllWard() {
        return wardRepository.getAllWards();
    }

    public WardDTO getWardById(Integer id) {
        return new WardDTO(wardRepository.getById(id));
    }

    @Transactional
    public WardDTO postWard(UpsertWardRequest request) {
        District district = districtRepository.getById(request.getDistrictId());
        Ward ward = Ward.builder()
                .name(request.getName())
                .district(district)
                .build();
        wardRepository.save(ward);
        return new WardDTO(ward);
    }

    @Transactional
    public WardDTO updateWard(Integer id, UpsertWardRequest request) {
        Ward ward = wardRepository.getById(id);
        District district = districtRepository.getById(request.getDistrictId());
        ward.setName(request.getName());
        ward.setDistrict(district);
        wardRepository.save(ward);
        return new WardDTO(ward);
    }

    @Transactional
    public void deleteWard(Integer id) {
        Ward ward = wardRepository.getById(id);
        wardRepository.delete(ward);
    }

    public List<WardDTO> getWardsByDistrictId(Integer id) {
        return wardRepository.findWardsByDistrictId(id);
    }
}
