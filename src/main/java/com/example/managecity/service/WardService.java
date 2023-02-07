package com.example.managecity.service;

import com.example.managecity.entity.Ward;
import com.example.managecity.exception.NotFoundException;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertWardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardService {
    private final WardRepository wardRepository;
    public List<Ward> getAllWard() {
        return wardRepository.findAll();
    }

    public Ward getWardById(Integer id) {
        return wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        });
    }

    @Transactional
    public Ward postWard(UpsertWardRequest request) {
        Ward ward = Ward.builder()
                .name(request.getName())
                .build();
        return wardRepository.save(ward);
    }

    @Transactional
    public Ward updateWard(Integer id, UpsertWardRequest request) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        });
        ward.setName(request.getName());
        return wardRepository.save(ward);
    }

    @Transactional
    public void deleteWard(Integer id) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Ko co ward voi id = " + id);
        });
        wardRepository.delete(ward);
    }
}
