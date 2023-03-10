package com.example.managecity.dto;

import com.example.managecity.entity.District;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private Integer id;
    private String name;
    private Integer cityId;
    private List<WardDTO> wardDto;

    public DistrictDTO(District entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setCityId(entity.getCity().getId());
        wardDto.addAll(entity.getWards().stream().map(e -> new WardDTO(e)).collect(Collectors.toList()));
    }
}
