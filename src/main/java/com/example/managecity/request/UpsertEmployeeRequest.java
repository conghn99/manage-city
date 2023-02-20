package com.example.managecity.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertEmployeeRequest {
    private String code;
    private String name;
    private String email;
    private int age;
    private String phone;
    private Integer cityId;
    private Integer districtId;
    private Integer wardId;
}
