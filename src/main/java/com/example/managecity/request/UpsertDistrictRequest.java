package com.example.managecity.request;

import com.example.managecity.entity.Ward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertDistrictRequest {
    private String name;
    private Integer cityId;
    private List<Ward> wards;
}
