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
        return new WardDTO(wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        }));
    }

    @Transactional
    public WardDTO postWard(UpsertWardRequest request) {
        District district = districtRepository.findById(request.getDistrictId()).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + request.getDistrictId());
        });
        Ward ward = Ward.builder()
                .name(request.getName())
                .district(district)
                .build();
        wardRepository.save(ward);
        return new WardDTO(ward);
    }

    @Transactional
    public WardDTO updateWard(Integer id, UpsertWardRequest request) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        });
        District district = districtRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co district voi id = " + id);
        });
        ward.setName(request.getName());
        ward.setDistrict(district);
        wardRepository.save(ward);
        return new WardDTO(ward);
    }

    @Transactional
    public void deleteWard(Integer id) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        });
        wardRepository.delete(ward);
    }

    public List<WardDTO> getWardsByDistrictId(Integer id) {
        List<Ward> wards = wardRepository.findWardsByDistrictId(id);
        List<WardDTO> dtoWards = new ArrayList<>();
        wards.forEach(ward -> {
            WardDTO wardDTO = convertToDTO(ward);
            dtoWards.add(wardDTO);
        });
        return dtoWards;
    }

    private WardDTO convertToDTO(Ward ward) {
        WardDTO dto = new WardDTO();
        dto.setId(ward.getId());
        dto.setName(ward.getName());
        return dto;
    }
}
