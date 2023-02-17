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
    @Size(min = 6, max = 10, message = "Code must be be between 6 and 10 character")
    @Pattern(regexp = "^\\S*$", message = "Code must not contain whitespace.")
    private String code;

    @NotBlank(message = "name must not blank")
    private String name;

    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message="Invalid email address")
    @NotBlank(message = "email must not blank")
    private String email;

    @Positive(message = "age must be greater than 0")
    private int age;

    @NotBlank(message = "phone must not blank")
    @Digits(integer = 10, fraction = 0, message = "invalid phone number")
    private String phone;

    @NotBlank(message = "city must not blank")
    private String city;

    @NotBlank(message = "district must not blank")
    private String district;

    @NotBlank(message = "ward must not blank")
    private String ward;
}
