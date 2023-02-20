package com.example.managecity.validate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseStatus {
    EMPLOYEE_CODE_ALREADY_EXIST(40001, "This code already exists."),
    EMPLOYEE_CODE_IS_NULL(40002,"Employee code not null"),
    EMPLOYEE_CODE_WRONG_FORMAT(40003, "Incorrect formatted code (no white space, length 6-10 characters)"),
    EMPLOYEE_NAME_IS_NULL(40004,"Employee name not null"),
    EMPLOYEE_EMAIL_IS_NULL(40005,"Email not null"),
    EMAIL_WRONG_FORMAT(40006, "Incorrect email format"),
    EMPLOYEE_PHONE_IS_NULL(40007,"Phone not null"),
    PHONE_WRONG_FORMAT(40008, "Incorrect phone format (max 11 number)"),
    EMPLOYEE_AGE_IS_NULL(40009,"Age not null"),
    EMPLOYEE_AGE_WRONG_FORMAT(40010, "Age must greater than 0"),
    CITY_NOT_EXIST(40011, "City not exist"),
    DISTRICT_NOT_EXIST(40012, "District not exist"),
    WARD_NOT_EXIST(40013, "Ward not exist"),
    HAS_CITY_PROVINCE_WARD_ID(40014,"Id city, district, ward not null"),
    CERTIFICATE_ALREADY_EXIST(40015, "The employee already has this certification"),
    HAVE_3_CERTIFICATE(40016, "Already have 3 diplomas of the same type that are still valid, can't add more"),
    EXCEL_ERROR(40017, ""),
    CELL_IS_NULL(40018, "Information is blank"),
    FILE_ERROR(400, "Please upload an excel file"),
    OK(200, "success");

    private int code;
    private String message;
}
