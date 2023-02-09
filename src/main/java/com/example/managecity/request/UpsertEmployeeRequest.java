package com.example.managecity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
