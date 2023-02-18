package com.example.managecity.dto;

import com.example.managecity.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String code;
    private String email;
    private int age;
    private String phone;
    private Integer cityId;
    private Integer districtId;
    private Integer wardId;

    public EmployeeDTO(Employee entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setCode(entity.getCode());
        this.setEmail(entity.getEmail());
        this.setAge(entity.getAge());
        this.setPhone(entity.getPhone());
        this.setCityId(entity.getCity().getId());
        this.setDistrictId(entity.getDistrict().getId());
        this.setWardId(entity.getDistrict().getId());
    }
}
